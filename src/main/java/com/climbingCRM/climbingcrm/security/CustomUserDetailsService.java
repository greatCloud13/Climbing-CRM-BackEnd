package com.climbingCRM.climbingcrm.security;

import com.climbingCRM.climbingcrm.entity.user.User;
import com.climbingCRM.climbingcrm.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * Spring Security 인증을 위한 사용자 정보 조회 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

    /**
     * username으로 사용자 정보 조회
     * 
     * @param username 사용자명
     * @return UserDetails 사용자 상세 정보
     * @throws UsernameNotFoundException 사용자를 찾을 수 없는 경우
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. DB에서 사용자 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다: "+username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user){
        return Collections.singleton(
                new SimpleGrantedAuthority(user.getRole().name())
        );
    }
}
