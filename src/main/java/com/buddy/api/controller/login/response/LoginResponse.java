package com.buddy.api.controller.login.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private Boolean isSuccess;
    private TokenResponse result;

    public LoginResponse(TokenResponse result) {
        this.isSuccess = true;
        this.result = result;
    }

    public static LoginResponse of(TokenResponse tokenResponse) {
        return new LoginResponse(tokenResponse);
    }
}
