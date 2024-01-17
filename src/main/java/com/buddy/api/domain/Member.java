package com.buddy.api.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @Column(unique = true)
    @NotNull
    private String memberId;
    @NotNull
    private String password;
    @NotNull
    private String nickname;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String birthdate;
    private String created;
    @NotNull
    private String role;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Auction> auction;

    public Member() {
    }
    @Builder
    public Member(String memberId, String password,String nickname, String email, String birthdate, String created) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
        this.created = created;
        this.role = "USER";
    }
    public static Member of(String memberId, String password,String nickname, String email, String birthdate) {
        return new Member(memberId, password, nickname, email, birthdate, LocalDateTime.now().toString());
    }

    public void updateTo(String memberId, String password,String nickname, String email, String birthdate) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birthdate = birthdate;
    }

    public void addProfile(Profile profile) {
        this.profile = profile;
    }
}
