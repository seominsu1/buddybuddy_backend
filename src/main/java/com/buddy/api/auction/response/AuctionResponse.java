package com.buddy.api.auction.response;

import com.buddy.api.domain.Auction;
import com.buddy.api.domain.Member;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class AuctionResponse {

    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime bidEndDate;
    private String title;
    private String content;
    private String bidder;
    private int price;

    public AuctionResponse(String nickname, LocalDateTime bidEndDate, String title, String content, String bidder, int price ) {
        this.nickname = nickname;
        this.bidEndDate = bidEndDate;
        this.title = title;
        this.content = content;
        this.bidder = bidder;
        this.price = price;
    }

    public static AuctionResponse of(Auction auction) {
        Member member = auction.getMember();
        return new AuctionResponse(member.getNickname() ,auction.getBidEndTime(),
                auction.getTitle(), auction.getContent(),auction.getBidder(), auction.getPrice());
    }
}
