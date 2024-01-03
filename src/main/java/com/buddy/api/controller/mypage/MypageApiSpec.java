package com.buddy.api.controller.mypage;

import org.springframework.http.ResponseEntity;

public interface MypageApiSpec {
    ResponseEntity getInfo(String memberId);
}
