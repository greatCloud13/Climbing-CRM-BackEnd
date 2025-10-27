package com.climbingCRM.climbingcrm.service;

import com.climbingCRM.climbingcrm.common.annotations.InjectUserContext;
import com.climbingCRM.climbingcrm.common.context.UserContext;
import com.climbingCRM.climbingcrm.common.contextholder.UserContextHolder;
import com.climbingCRM.climbingcrm.dto.auth.CurrentUserProfile;
import com.climbingCRM.climbingcrm.dto.auth.LoginRequest;
import com.climbingCRM.climbingcrm.dto.auth.LoginResponse;
import com.climbingCRM.climbingcrm.dto.auth.SignUpRequest;
import com.climbingCRM.climbingcrm.entity.BranchCenter;
import com.climbingCRM.climbingcrm.entity.user.Role;
import com.climbingCRM.climbingcrm.entity.user.User;
import com.climbingCRM.climbingcrm.repository.user.BranchCenterRepository;
import com.climbingCRM.climbingcrm.repository.user.UserRepository;
import com.climbingCRM.climbingcrm.security.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 관련 비즈니스 로직
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BranchCenterRepository branchCenterRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     * @param request 회원가입 요청 정보
     * @throws IllegalArgumentException username 또는 email 중복된 경우
     */
    @Transactional
    public void signup(SignUpRequest request){

        // username 중복체크
        if(userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("이미 사용중인 사용자명입니다.");
        }

        // email 중복체크
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        BranchCenter center = branchCenterRepository.findById(request.getBranchCenterId())
                .orElseThrow(()-> new EntityNotFoundException("지점을 찾을 수 없습니다."));

        

        // User 엔티티 생성 (비밀번호 암호화)
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ROLE_USER)
                .branchCenter(center)
                .build();

        userRepository.save(user);
    }

    /**
     * 로그인
     * 
     * @param request 로그인 요청 정보
     * @return JWT 토큰이 포함된 로그인 응답
     */
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request){
        // 1. 인증 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                );

        // 2. 인증 처리 (내부적으로 CustomUserDetailsService.loadUserByUsername() 호출
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 3. 인증 성공 시 JWT 토큰 생성
        String username = authentication.getName();
        String token = jwtTokenProvider.generateToken(username);

        // 4. 응답 생서
        return new LoginResponse(token, username);
        
    }

    @InjectUserContext
    @Transactional
    public CurrentUserProfile getCurrentUserProfile(){
        UserContext userContext = UserContextHolder.getCurrentUser();

        User user = userRepository.findById(userContext.getId())
                .orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        return CurrentUserProfile.from(user);
    }


}
