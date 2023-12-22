package com.example.springplus.global.exception.user;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class AuthenticationNotmatchException extends BusinessException {

    public AuthenticationNotmatchException() {
        super(ErrorCode.AUTHENTICATION_NOTMATCH_EXCEPTION);
    }
}
