package com.example.springplus.global.exception.like;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class NotFoundLikeException extends BusinessException {

    public NotFoundLikeException() {
        super(ErrorCode.NOT_FOUND_LIKE_EXCEPTION);
    }
}
