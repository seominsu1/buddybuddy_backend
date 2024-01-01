package com.buddy.api.controller.member;

import com.buddy.api.controller.member.request.MemberRequest;
import com.buddy.api.controller.member.response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Member", description = "memberController")
public interface MemberApiSpec {

    @Operation(summary = "멤버 조회", description = "멤버 조회")
    ResponseEntity<MemberResponse> findById(String memberId);

    @Operation(summary = "멤버 생성", description = "멤버 생성")
    ResponseEntity save(MemberRequest request);

    @Operation(summary = "멤버 삭제", description = "멤버 삭제")
    ResponseEntity delete(MemberRequest request);

    @Operation(summary = "멤버 정보 수정", description = "멤버 정보 수정")
    ResponseEntity update(String originMemberId, MemberRequest request);
}
