package com.climbingCRM.climbingcrm.dto.auth;

import com.climbingCRM.climbingcrm.entity.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Builder
public class CurrentUserProfile {

    /**
     * 사용자 고유 ID
     */
    private Long id;

    /**
     * 사용자 아이디
     */
    private String username;

    /**
     * 이메일
     */
    private String email;

    /**
     * 사용자명
     */
    private String name;

    /**
     * 역할
     */
    private String role;

    /**
     * 관리 지점 ID
     */
    private Long branchCenterId;

    /**
     * 관리 지점
     */
    private String branchCenterName;

    public static CurrentUserProfile from(User user){
        return CurrentUserProfile.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().toString())
                .branchCenterId(user.getBranchCenter().getId())
                .branchCenterName(user.getBranchCenter().getCenterName())
                .build();

    }
}
