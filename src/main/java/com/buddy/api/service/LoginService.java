package com.buddy.api.service;

import com.buddy.api.config.jwt.JwtTokenProvider;
import com.buddy.api.controller.login.request.LoginRequest;
import com.buddy.api.controller.login.response.TokenResponse;
import com.buddy.api.domain.Member;
import com.buddy.api.exception.BadCredentialsException;
import com.buddy.api.exception.DuplicatedMemberException;
import com.buddy.api.exception.MemberNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse login(LoginRequest loginRequest, HttpServletResponse response) throws RuntimeException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getMemberId(),
                            loginRequest.getPassword()
                    )
            );
            TokenResponse tokenResponse = new TokenResponse(
                    jwtTokenProvider.createAccessToken(authentication),
                    jwtTokenProvider.createRefreshToken(authentication)
            );
            response.addHeader("Authorization","Bearer "+tokenResponse.getAccessToken());
            return tokenResponse;

        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            throw new BadCredentialsException();
        }
    }
}
