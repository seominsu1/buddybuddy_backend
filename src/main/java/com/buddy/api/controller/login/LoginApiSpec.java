package com.buddy.api.controller.login;

import com.buddy.api.controller.login.request.LoginRequest;
import com.buddy.api.controller.login.response.LoginResponse;
import com.buddy.api.controller.member.request.MemberRequest;
import com.buddy.api.controller.member.response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Login", description = "LoginController")
public interface LoginApiSpec {

    @Operation(summary = "로그인", description = "로그인")
    ResponseEntity<LoginResponse> login(LoginRequest request, HttpServletResponse response);
}
