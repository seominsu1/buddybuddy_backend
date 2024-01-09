package com.buddy.api.controller.common;

import lombok.Getter;

@Getter
public class CommonResponse {
    private Boolean isSuccess;
    private String message;

    public CommonResponse(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static CommonResponse of(Boolean isSuccess, String message) {
        return new CommonResponse(isSuccess, message);
    }
}
