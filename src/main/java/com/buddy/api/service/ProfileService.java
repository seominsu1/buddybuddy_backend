package com.buddy.api.service;

import com.buddy.api.controller.mypage.response.MypageResponse;
import com.buddy.api.controller.profile.request.ProfileRequest;
import com.buddy.api.domain.LicenseLevel;
import com.buddy.api.domain.Member;
import com.buddy.api.domain.Profile;
import com.buddy.api.exception.LicenseNotFoundException;
import com.buddy.api.exception.MemberNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import com.buddy.api.repository.profile.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ProfileService {

    private ProfileRepository profileRepository;
    private MemberRepository memberRepository;

    public ProfileService(ProfileRepository profileRepository, MemberRepository memberRepository) {
        this.profileRepository = profileRepository;
        this.memberRepository = memberRepository;
    }
    @Transactional
    public Profile create(String memberId, ProfileRequest request) {
        validateProfile(request);
        return profileRepository.save(Profile.of(request.getLevel(), request.getGender(), request.getRegion(),
                memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId))));
    }

    private void validateProfile(ProfileRequest request) {
        if (!LicenseLevel.isExist(request.getLevel())) {
            throw new LicenseNotFoundException();
        }
    }

    @Transactional
    public Profile update(String memberId, ProfileRequest request) {
        validateProfile(request);
        log.info("프로필 찾기 sql 시작");
        Profile profile = profileRepository.findByMemberId(memberId);
        log.info("프로필 찾기 sql 끝");
        profile.updateTo(request.getLevel(),request.getGender(),request.getRegion(),
                memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId)));
        return profile;
    }

    public MypageResponse findInfo(String memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId));
        return MypageResponse.of(findMember.getMemberId(), findMember.getNickname(), findMember.getEmail(), findMember.getBirthdate(),findMember.getProfile().getLevel(),findMember.getProfile().getGender(),findMember.getProfile().getRegion());
    }
}
