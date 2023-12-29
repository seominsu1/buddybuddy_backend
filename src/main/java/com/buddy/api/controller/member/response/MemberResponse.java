package com.buddy.api.controller.member.response;

import com.buddy.api.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String memberId;
    private String nickname;
    private String email;
    private String birthdate;

    public MemberResponse(String memberId, String nickname, String email, String birthdate) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getMemberId(), member.getNickname(), member.getEmail(), member.getBirthdate());
    }
}
