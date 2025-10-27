package com.climbingCRM.climbingcrm.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "사용자 아이디는 필수입니다.")
    @Size(min = 4, max = 20, message = "사용자명은 4~20자여야 합니다.")
    private String username;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 10, message = "이름은 최대 10자입니다.")
    private String name;

    @NotBlank(message ="비밀번호는 필수 입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이여야 합니다.")
    private String password;

    @NotBlank(message ="이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotNull(message = "센터 지정은 필수입니다.")
    private Long branchCenterId;
}
