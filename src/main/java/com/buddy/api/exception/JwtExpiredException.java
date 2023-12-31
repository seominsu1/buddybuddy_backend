package com.buddy.api.exception;

public class JwtExpiredException extends RuntimeException{
    private static final String JWT_EXPIRED_MESSAGE = "[error] jwt 가 만료되었습니다.";

    public JwtExpiredException() {
        super(JWT_EXPIRED_MESSAGE);
    }
}
