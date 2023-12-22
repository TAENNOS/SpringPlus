package com.example.springplus.global.exception.user;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class PasswordNotmatchException extends BusinessException {

    public PasswordNotmatchException() {
        super(ErrorCode.PASSWORD_NOTMATCH_EXCEPTION);
    }
}