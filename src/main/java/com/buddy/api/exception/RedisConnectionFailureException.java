package com.buddy.api.exception;

public class RedisConnectionFailureException extends RuntimeException{
    private static final String REDIS_CONNECTION_FAILURE_MESSAGE = "[error] redis 연결에 실패했습니다.";

    public RedisConnectionFailureException() {
        super(REDIS_CONNECTION_FAILURE_MESSAGE);
    }
}
