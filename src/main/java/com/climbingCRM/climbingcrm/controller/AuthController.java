package com.climbingCRM.climbingcrm.controller;

import com.climbingCRM.climbingcrm.dto.auth.*;
import com.climbingCRM.climbingcrm.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 관련 API 엔드포인트
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입
     * 
     * @param request 회원가입 요청 정보
     * @return 회원가입 결과 메시지
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignUpRequest request){

        authService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("회원가입이 완료되었습니다"));
    }

    /**
     * 로그인
     *
     * @param request 로그인 요청 정보
     * @return JWT 토큰 및 사용자 정보
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 현재 로그인한 사용자의 정보를 조회
     *
     * @return 현재 사용자 상세 정보
     */
    @GetMapping("/me")
    public ResponseEntity<CurrentUserProfile> getCurrentUser(){
        CurrentUserProfile result = authService.getCurrentUserProfile();

        return ResponseEntity.ok(result);
    }

}
