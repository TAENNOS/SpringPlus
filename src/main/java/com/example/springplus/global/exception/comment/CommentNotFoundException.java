package com.example.springplus.global.exception.comment;

import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.global.exception.common.ErrorCode;

public class CommentNotFoundException extends BusinessException {

    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND_EXCEPTION);
    }
}