package com.buddy.api.domain;

import com.buddy.api.controller.post.request.PostRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pool_id", referencedColumnName = "pool_id")
    private Pool pool;
    private LocalDateTime date;
    private String buddyLevel;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Boolean isClosed;
    private LocalDateTime postDate;
    private String openTalkUrl;

    public Post() {
    }
    public Post(Member member, Pool pool, LocalDateTime date, String buddyLevel, String content, String openTalkUrl) {
        this.member = member;
        this.pool = pool;
        this.date = date;
        this.buddyLevel = buddyLevel;
        this.content = content;
        this.isClosed = false;
        this.postDate = LocalDateTime.now();
        this.openTalkUrl = openTalkUrl;
    }

    public static Post of(Member member,Pool pool, LocalDateTime date, String buddyLevel, String content, String openTalkUrl) {
        return new Post(member, pool, date, buddyLevel, content, openTalkUrl);
    }

    public void updateTo(Pool pool, PostRequest request) {
        this.pool = pool;
        this.date = request.getDate();
        this.buddyLevel = request.getBuddyLevel();
        this.content = request.getContent();
        this.openTalkUrl = request.getOpenTalkUrl();
    }
}
