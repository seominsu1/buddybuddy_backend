package com.buddy.api.exception;

public class BadCredentialsException extends RuntimeException{
    private static final String BAD_CREDENTIAL_MESSAGE = "[error] 로그인에 실해했습니다.";

    public BadCredentialsException() {
        super(BAD_CREDENTIAL_MESSAGE);
    }
}
