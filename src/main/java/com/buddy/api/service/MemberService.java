package com.buddy.api.service;

import com.buddy.api.domain.Member;
import com.buddy.api.exception.DuplicatedMemberException;
import com.buddy.api.exception.MemberNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Member findMemberById(String memberId) {
        return findById(memberId);
    }

    private Member findById(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Transactional
    public Member save(String memberId, String password, String nickname, String email, String birthdate) {
        checkDuplicatedMember(memberId);
        return saveMember(memberId, passwordEncoder.encode(password), nickname, email, birthdate);
    }

    private void checkDuplicatedMember(String memberId) {
        if (memberRepository.existsById(memberId)) {
            throw new DuplicatedMemberException(memberId);
        }
    }

    private Member saveMember(String memberId, String password, String nickname, String email, String birthdate) {
        return memberRepository.save(Member.of(memberId, password, nickname, email, birthdate));
    }

    @Transactional
    public void delete(String memberId) {
        memberRepository.delete(findById(memberId));
    }

    @Transactional
    public Member update(String originMemberId, String memberId, String password, String nickname, String email, String birthdate) {
        Member findMember = memberRepository.findById(originMemberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        findMember.updateTo(memberId, passwordEncoder.encode(password), nickname, email, birthdate);
        return findMember;
    }
}
