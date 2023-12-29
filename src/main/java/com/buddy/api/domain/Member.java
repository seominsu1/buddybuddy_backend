package com.buddy.api.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
@Entity
@Getter
public class Member {

    @Id
    @Column(name = "member_id", nullable = false, unique = true)
    @NotNull
    private String memberId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthdate", nullable = false)
    private String birthdate;

    @Column(name = "created")
    private String created;

    public Member() {
    }
    @Builder
    public Member(String memberId, String password, String email, String birthdate, String created) {
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.created = created;
    }


}
