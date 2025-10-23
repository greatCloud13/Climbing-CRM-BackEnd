package com.climbingCRM.climbingcrm.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 인증 관련 응답 DTO
 */
@Getter
@AllArgsConstructor
public class AuthResponse {

    /**
     * 응답 메시지
     */
    private String Message;

    // 필요시 추가
    // private String username;
    // private LocalDateTime timestamp;

}
