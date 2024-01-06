package com.buddy.api.controller.post.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class PostRequest {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime date;
    private String poolName;
    private String buddyLevel;
    private String content;
    private String openTalkUrl;

    public PostRequest(LocalDateTime date, String poolName, String buddyLevel, String content, String openTalkUrl) {
        this.date = date;
        this.poolName = poolName;
        this.buddyLevel = buddyLevel;
        this.content = content;
        this.openTalkUrl = openTalkUrl;
    }
}
