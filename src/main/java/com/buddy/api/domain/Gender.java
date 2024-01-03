package com.buddy.api.domain;

public enum Gender {
    MALE("M"),
    FEMALE("F");
    private String genderType;

    Gender(String genderType) {
        this.genderType = genderType;
    }

    public String getGenderType() {
        return genderType;
    }
}
