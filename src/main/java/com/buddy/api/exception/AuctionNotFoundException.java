package com.buddy.api.exception;

public class AuctionNotFoundException extends RuntimeException{
    private static final String AUCTION_NOT_FOUND_MESSAGE = "[error] 해당 경매는 존재하지 않습니다.";

    public AuctionNotFoundException() {
        super(AUCTION_NOT_FOUND_MESSAGE);
    }
}
