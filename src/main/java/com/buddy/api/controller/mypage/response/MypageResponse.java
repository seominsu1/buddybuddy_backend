package com.buddy.api.controller.mypage.response;

import lombok.Getter;

@Getter
public class MypageResponse {

    private String memberId;
    private String nickname;
    private String email;
    private String birthdate;
    private String level;
    private String gender;
    private String region;

    public MypageResponse(String memberId, String nickname, String email, String birthdate, String level, String gender, String region) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
        this.level = level;
        this.gender = gender;
        this.region = region;
    }

    public static MypageResponse of(String memberId, String nickname, String email, String birthdate, String level, String gender, String region ) {
        return new MypageResponse(memberId, nickname, email, birthdate, level, gender, region);
    }
}
