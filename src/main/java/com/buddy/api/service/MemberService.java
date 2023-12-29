package com.buddy.api.service;

import com.buddy.api.domain.Member;
import com.buddy.api.exception.DuplicatedMemberException;
import com.buddy.api.exception.MemberNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMember(String memberId) {
        checkIsValidMember(memberId);
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public Member save(String memberId, String password, String nickname, String email, String birthdate) {
        checkDuplicatedMember(memberId);
        return saveMember(memberId, password, nickname, email, birthdate);
    }

    private Member saveMember(String memberId, String password, String nickname, String email, String birthdate) {
        return memberRepository.save(Member.of(memberId, password, nickname, email, birthdate));
    }

    private void checkDuplicatedMember(String memberId) {
        if (memberRepository.existsById(memberId)) {
            throw new DuplicatedMemberException(memberId);
        }
    }

    private void checkIsValidMember(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberNotFoundException(memberId);
        }
    }
}
