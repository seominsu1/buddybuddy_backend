package com.buddy.api.exception;

public class DuplicatedMemberException extends RuntimeException{
    private static final String DUPLICATED_MEMBER_MESSAGE = "중복된 아이디가 존재합니다.";

    public DuplicatedMemberException(String memberId) {
        super(DUPLICATED_MEMBER_MESSAGE);
    }
}
