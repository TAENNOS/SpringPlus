package com.example.springplus.user.dto;

import lombok.Getter;
@Getter
public class SignupRequestDTO {

    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$",
        message = "유저네임은 알파벳 소문자와 대문자, 숫자만 입력가능합니다. 글자수는 3글자 이상 10글자 이하이어야 입니다. ")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{4,}$",
        message = "비밀번호는 알파벳 소문자와 대문자, 숫자만 입력가능합니다. 글자수는 4글자 이상이어야 합니다.")
    private String password;

    private String checkPassword;
}