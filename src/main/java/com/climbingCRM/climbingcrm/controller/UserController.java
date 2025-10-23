package com.climbingCRM.climbingcrm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 관련 API
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok()
                .body(new ProfileResponse(
                        userDetails.getUsername(),
                        userDetails.getAuthorities().toString()
                ));
    }

    record ProfileResponse(String username, String authorities){}

}
