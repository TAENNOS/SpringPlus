package com.example.springplus.global.exception.like;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class DuplicatedLikeException extends BusinessException {

    public DuplicatedLikeException() {
        super(ErrorCode.DUPLICATED_LIKE_EXCEPTION);
    }
}
