package com.example.springplus.global.exception.jwt;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class UnsupportedJwtTokenException extends BusinessException {

    public UnsupportedJwtTokenException(Throwable cause) {
        super(ErrorCode.UNSUPPORTED_JWT_TOKEN_EXCEPTION, cause);
    }
}
