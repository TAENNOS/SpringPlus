package com.example.springplus.global.exception.like;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class SelfLikeException extends BusinessException {


    public SelfLikeException() {
        super(ErrorCode.SELF_LIKE_EXCEPTION);
    }
}
