package com.ezace.vendingmachine.domain.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data

public class JoinForm {
    private Long id;
    @NotBlank(message = "패스워드를 입력해 주세요." )
    private String password;
    @NotBlank(message = "패스워드 확인을 위해 입력해 주세요.")
    private String password2;
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    private String email;
    private String nickname;
    private String phone;
}
