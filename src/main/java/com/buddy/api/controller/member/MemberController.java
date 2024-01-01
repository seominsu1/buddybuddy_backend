package com.buddy.api.controller.member;

import com.buddy.api.controller.member.request.MemberRequest;
import com.buddy.api.controller.member.response.MemberResponse;
import com.buddy.api.repository.member.MemberRepository;
import com.buddy.api.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/member")
public class MemberController implements MemberApiSpec{

    private MemberRepository memberRepository;
    private MemberService memberService;

    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Override
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> findById(@PathVariable String memberId) {
        return ResponseEntity.ok(MemberResponse.of(memberService.findMemberById(memberId)));
    }

    @Override
    @PostMapping
    public ResponseEntity<MemberResponse> save(@RequestBody MemberRequest request) {
        return ResponseEntity.ok(MemberResponse.of(memberService.save(request.getMemberId(),
                request.getPassword(),request.getNickname(),
                request.getEmail(), request.getBirthdate())));
    }

    @Override
    @DeleteMapping
    public ResponseEntity delete(@RequestBody MemberRequest request) {
        memberService.delete(request.getMemberId());
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{memberId}")
    public ResponseEntity update(@PathVariable String originMemberId, @RequestBody MemberRequest request) {
        return ResponseEntity.ok(MemberResponse.of(memberService.update(originMemberId, request.getMemberId(),
                request.getPassword(),request.getNickname(),
                request.getEmail(), request.getBirthdate())));
    }
}
