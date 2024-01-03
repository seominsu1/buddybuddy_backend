package com.buddy.api.controller.profile.response;

import com.buddy.api.domain.Profile;
import lombok.Getter;

@Getter
public class ProfileResponse {
    private String level;
    private String gender;
    private String region;

    public ProfileResponse(String level, String gender, String region) {
        this.level = level;
        this.gender = gender;
        this.region = region;
    }

    public static ProfileResponse of(Profile profile) {
        return new ProfileResponse(profile.getLevel(),profile.getGender(),profile.getRegion());
    }
}
