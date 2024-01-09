package com.buddy.api.controller.post.response;


import com.buddy.api.controller.pool.response.PoolResponse;
import com.buddy.api.controller.profile.response.ProfileResponse;
import com.buddy.api.domain.Member;
import com.buddy.api.domain.Post;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private String nickname;
    private ProfileResponse writerDetails;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    private String content;
    private String buddyLevel;
    private PoolResponse poolDetails;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime postDate;
    private Boolean isClosed;
    private String openTalkUrl;

    public PostResponse(String nickname, ProfileResponse writerDetails, LocalDateTime date, String content, String buddyLevel, PoolResponse poolDetails, LocalDateTime postDate, Boolean isClosed, String openTalkUrl) {
        this.nickname = nickname;
        this.writerDetails = writerDetails;
        this.date = date;
        this.content = content;
        this.buddyLevel = buddyLevel;
        this.poolDetails = poolDetails;
        this.postDate = postDate;
        this.isClosed = isClosed;
        this.openTalkUrl = openTalkUrl;
    }

    public static PostResponse of(Post post) {
        Member member = post.getMember();
        return new PostResponse(member.getNickname(), ProfileResponse.of(member.getProfile())
                ,post.getDate(),post.getContent(),post.getBuddyLevel(),PoolResponse.of(post.getPool()),
                post.getPostDate(),post.getIsClosed(),post.getOpenTalkUrl());
    }
}
