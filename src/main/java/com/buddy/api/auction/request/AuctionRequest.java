package com.buddy.api.auction.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AuctionRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime bidEndDate;
    private String title;
    private String content;

    public AuctionRequest(LocalDateTime bidEndDate,String title, String content) {
        this.bidEndDate = bidEndDate;
        this.title = title;
        this.content = content;
    }
}
