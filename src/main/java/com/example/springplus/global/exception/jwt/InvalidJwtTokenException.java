package com.example.springplus.global.exception.jwt;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class InvalidJwtTokenException extends BusinessException {

    public InvalidJwtTokenException(Throwable cause) {
        super(ErrorCode.INVALID_JWT_TOKEN_EXCEPTION, cause);
    }
}