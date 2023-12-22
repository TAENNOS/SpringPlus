package com.example.springplus.global.exception.jwt;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class InvalidJwtSignatureException extends BusinessException {

    public InvalidJwtSignatureException(Throwable cause) {
        super(ErrorCode.EXPIRED_JWT_TOKEN_EXCEPTION, cause);
    }
}
