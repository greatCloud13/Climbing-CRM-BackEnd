package com.climbingCRM.climbingcrm.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 응답 DTO
 */
@Getter
@AllArgsConstructor
public class LoginResponse {

    /**
     * JWT 엑세스 토큰
     */
    private String accessToken;

    /**
     * 사용자명
     */
    private String username;

    /**
     * 토큰 타입; (Bearer)
     */
    private String tokenType;

    public LoginResponse(String accessToken, String username){
        this.accessToken = accessToken;
        this.username = username;
        this.tokenType = "Bearer";
    }

}
