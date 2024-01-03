package com.buddy.api.controller.mypage;

import com.buddy.api.controller.mypage.response.MypageResponse;
import com.buddy.api.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController implements MypageApiSpec{

    private final ProfileService profileService;
    @Override
    @GetMapping("/{memberId}")
    public ResponseEntity<MypageResponse> getInfo(@PathVariable String memberId) {
        return ResponseEntity.ok(profileService.findInfo(memberId));
    }
}
