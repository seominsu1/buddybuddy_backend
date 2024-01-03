package com.buddy.api.controller.profile;

import com.buddy.api.controller.profile.request.ProfileRequest;
import com.buddy.api.controller.profile.response.ProfileResponse;
import com.buddy.api.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController implements ProfileApiSpec {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    @PostMapping("/{memberId}")
    public ResponseEntity<ProfileResponse> create(@PathVariable String memberId, @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(ProfileResponse.of(profileService.create(memberId, request)));
    }

    @Override
    @PutMapping("/{memberId}")
    public ResponseEntity<ProfileResponse> update(@PathVariable String memberId, @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(ProfileResponse.of(profileService.update(memberId, request)));
    }
}
