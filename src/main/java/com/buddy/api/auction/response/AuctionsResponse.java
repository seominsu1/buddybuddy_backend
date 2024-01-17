package com.buddy.api.auction.response;


import lombok.Getter;

import java.util.List;

@Getter
public class AuctionsResponse {
    private List<AuctionResponse> auctions;
    private Integer totalElement;

    public AuctionsResponse(List<AuctionResponse> auctions, Integer totalElement) {
        this.auctions = auctions;
        this.totalElement = totalElement;
    }

    public static AuctionsResponse of(List<AuctionResponse> auctions, Integer totalElement) {
        return new AuctionsResponse(auctions, totalElement);
    }
}
