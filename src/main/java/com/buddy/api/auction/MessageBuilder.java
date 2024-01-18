package com.buddy.api.auction;

public class MessageBuilder {

    private static final String SUCCESS_MESSAGE = "님의 입찰이 성공되었습니다.";
    private static final String FAIL_MESSAGE = "입찰이 실패했습니다.";
    private static final String PRICE_MESSAGE = " 현재가는 ";

    public String messageBuild(Boolean isSuccess, String bidder, String price) {
        if (isSuccess) {
            return bidder+SUCCESS_MESSAGE+PRICE_MESSAGE+price;
        }
        return FAIL_MESSAGE+PRICE_MESSAGE+price;
    }
}
