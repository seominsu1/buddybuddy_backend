package com.buddy.api.service.dto;

import com.buddy.api.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private String nickname;
    private String level;
    private String buddyLevel;
    private String poolName;
    private int depth;
    private LocalDateTime date;
    private LocalDateTime postDate;

    public PostDto(String nickname,String level, String buddyLevel, String poolName, int depth, LocalDateTime date, LocalDateTime postDate) {
        this.nickname = nickname;
        this.level = level;
        this.buddyLevel = buddyLevel;
        this.poolName = poolName;
        this.depth = depth;
        this.date = date;
        this.postDate = postDate;
    }

    public static PostDto of(Post post) {
        return new PostDto(post.getMember().getNickname(), post.getMember().getProfile().getLevel(),
                post.getBuddyLevel(), post.getPool().getName(), post.getPool().getDepth(),
                post.getDate(), post.getPostDate());
    }
}
