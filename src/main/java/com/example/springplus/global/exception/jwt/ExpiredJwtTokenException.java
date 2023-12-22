package com.example.springplus.global.exception.jwt;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class ExpiredJwtTokenException extends BusinessException {

    public ExpiredJwtTokenException(Throwable cause) {
        super(ErrorCode.EXPIRED_JWT_TOKEN_EXCEPTION, cause);
    }
}