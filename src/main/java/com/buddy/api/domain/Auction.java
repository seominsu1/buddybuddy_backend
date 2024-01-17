package com.buddy.api.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Auction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auction_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private Member member;
    @Column(columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private int price;
    private String bidder;
    private LocalDateTime bidEndTime;
    private LocalDateTime auctionPostDate;

    public Auction() {

    }

    public Auction(Member member, String title, String content, LocalDateTime bidEndTime) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.price = 0;
        this.bidder = "none";
        this.bidEndTime = bidEndTime;
        this.auctionPostDate = LocalDateTime.now();
    }

    public static Auction of(Member member, String title, String content, LocalDateTime bidEndTime) {
        return new Auction(member,title,content,bidEndTime);
    }

    public Boolean updateBid(int price, String bidder) {
        return true;
    }
}
