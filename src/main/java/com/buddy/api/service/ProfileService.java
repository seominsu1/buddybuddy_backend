package com.buddy.api.service;

import com.buddy.api.controller.profile.request.ProfileRequest;
import com.buddy.api.domain.LicenseLevel;
import com.buddy.api.domain.Profile;
import com.buddy.api.exception.LicenseNotFoundException;
import com.buddy.api.exception.MemberNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import com.buddy.api.repository.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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
}
