package com.buddy.api.service;

import com.buddy.api.domain.Member;
import com.buddy.api.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void create() {
        memberRepository.save(Member.of("testId", "pw", "test", "test@test", "000101"));
    }

    @DisplayName("아이디로 멤버 조회")
    @Test
    void findMember() {
        String memberId = "testId";

        MemberService memberService = new MemberService(memberRepository);
        Member member = memberService.findMember(memberId);

        assertThat(member.getMemberId()).isEqualTo(memberId);
    }
}