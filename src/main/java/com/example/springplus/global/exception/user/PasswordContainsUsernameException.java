package com.example.springplus.global.exception.user;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class PasswordContainsUsernameException extends BusinessException {

    public PasswordContainsUsernameException() {
        super(ErrorCode.PASSWORD_CONTAINS_USERNAME_EXCEPTION);
    }
}
