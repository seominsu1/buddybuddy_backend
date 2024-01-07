package com.buddy.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String level;
    @NotNull
    private String gender;
    private String region;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private Member member;

    public Profile() {
    }

    public Profile(String level, String gender, String region, Member member) {
        this.level = level;
        this.gender = gender;
        this.region = region;
        this.member = member;
    }
    public static Profile of(String level, String gender, String region, Member member) {
        Profile profile = new Profile(level, gender, region, member);
        member.addProfile(profile);
        return profile;
    }

    public void updateTo(String level, String gender, String region, Member member) {
        this.level = level;
        this.gender = gender;
        this.region = region;
        this.member = member;
        member.addProfile(this);
    }
}
