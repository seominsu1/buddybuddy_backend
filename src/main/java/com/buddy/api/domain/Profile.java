package com.buddy.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Profile {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String gender;
    @NotNull
    private String level;
    private String region;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private Member member;

    public Profile(String gender, String level, String region, Member member) {
        this.gender = gender;
        this.level = level;
        this.region = region;
        this.member = member;
    }
    public static Profile of(String gender, String level, String region, Member member) {
        Profile profile = new Profile(gender, level, region, member);
        member.addProfile(profile);
        return profile;
    }
}
