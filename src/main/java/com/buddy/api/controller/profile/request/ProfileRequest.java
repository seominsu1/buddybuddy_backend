package com.buddy.api.controller.profile.request;

import lombok.Getter;

@Getter
public class ProfileRequest {
    private String level;
    private String gender;
    private String region;

    public ProfileRequest(String level, String gender, String region) {
        this.level = level;
        this.gender = gender;
        this.region = region;
    }
}
