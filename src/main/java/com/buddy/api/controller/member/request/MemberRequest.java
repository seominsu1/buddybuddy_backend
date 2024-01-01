package com.buddy.api.controller.member.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class MemberRequest {

    private String memberId;
    private String password;
    private String nickname;
    private String email;
    private String birthdate;

    public MemberRequest(String memberId, String password, String nickname, String email, String birthdate) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
    }

    public static MemberRequest of(String memberId, String password, String nickname, String email, String birthdate) {
        return new MemberRequest(memberId, password, nickname, email, birthdate);
    }
}
