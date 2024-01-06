package com.buddy.api.controller.post.response;


import com.buddy.api.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {

    private Boolean isSuccess;
    private String message;


    public PostResponse(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static PostResponse of(Boolean isSuccess, String message) {
        return new PostResponse(isSuccess, message);
    }
}
