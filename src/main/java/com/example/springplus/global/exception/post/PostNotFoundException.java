package com.example.springplus.global.exception.post;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class PostNotFoundException extends BusinessException {

    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND_EXCEPTION);
    }
}
