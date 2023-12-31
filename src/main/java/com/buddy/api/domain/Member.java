package com.buddy.api.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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
}
