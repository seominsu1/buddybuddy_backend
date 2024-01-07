package com.buddy.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private Member member;
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
