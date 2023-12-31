package com.buddy.api.controller.login;

import com.buddy.api.controller.login.request.LoginRequest;
import com.buddy.api.controller.login.response.LoginResponse;
import com.buddy.api.controller.member.MemberApiSpec;
import com.buddy.api.controller.member.request.MemberRequest;
import com.buddy.api.controller.member.response.MemberResponse;
import com.buddy.api.repository.member.MemberRepository;
import com.buddy.api.service.LoginService;
import com.buddy.api.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/login")
public class LoginController implements LoginApiSpec {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(LoginResponse.of(loginService.login(request, response)));
    }

}
