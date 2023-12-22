package com.example.springplus.global.exception.user;

public class AlreadyExistUserException extends BusinessException {

    public AlreadyExistUserException() {
        super(ErrorCode.ALREADY_EXIST_MEMBER_EXCEPTION);
    }
}
