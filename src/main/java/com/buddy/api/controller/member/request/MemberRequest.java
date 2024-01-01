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
}
