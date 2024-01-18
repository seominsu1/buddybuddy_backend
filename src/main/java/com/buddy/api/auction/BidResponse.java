package com.buddy.api.auction;

import lombok.Getter;

@Getter
public class BidResponse {

    private String content;

    public BidResponse(String content) {
        this.content = content;
    }
}
