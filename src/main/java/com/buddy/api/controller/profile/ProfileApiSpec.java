package com.buddy.api.controller.profile;

import com.buddy.api.controller.profile.request.ProfileRequest;
import org.springframework.http.ResponseEntity;

public interface ProfileApiSpec {

    ResponseEntity create(String memberId, ProfileRequest request);
}
