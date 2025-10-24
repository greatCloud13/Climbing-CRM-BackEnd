package com.climbingCRM.climbingcrm.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 토큰 생성 및 검증 유틸리티
 */
@Slf4j
@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long expirationTime;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationTime){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = expirationTime;
    }

    /**
     * JWT 토큰 생성
     *
     * @param username 사용자명
     * @return JWT 토큰
     */
    public String generateToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+expirationTime);
        Map<String, Object> claims = new HashMap<>();


        return Jwts.builder()
                .subject(username)      // Payload: sub (토큰 주체)
                .claims(claims)
                .issuedAt(now)          // Payload: iat (발급 시간)
                .expiration(expiryDate) // Payload: exp (만료 시간)
                .signWith(secretKey)    // Signature: 서명
                .compact();             // 최종 토큰 문자열 생성
    }

    /**
     * JWT 토큰에서 username 추출
     *
     * @param token JWT 토큰
     * @return username
     */
    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)      // 서명 검증
                .build()
                .parseSignedClaims(token)   // 토큰 파싱
                .getPayload();              // Payload 추출

        return claims.getSubject();         // sub 필드 추출
    }

    /**
     * JWT 토큰 유효성 검증
     *
     * @param token JWT 토큰
     * @return 유효하면 true, 아니면 false
     */
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (Exception e){
            log.error("JWT 토큰 검증 실패: {}", e.getMessage());
            return false;
        }
    }
}
