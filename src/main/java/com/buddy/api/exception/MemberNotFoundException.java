package com.buddy.api.exception;

public class MemberNotFoundException extends RuntimeException{
    private static final String MEMBER_NOT_FOUND_MESSAGE = "라는 사용자를 찾을 수 없습니다.";

    public MemberNotFoundException(String memberId) {
        super(memberId + MEMBER_NOT_FOUND_MESSAGE);
    }
}
