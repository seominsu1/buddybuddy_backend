package com.buddy.api.repository.post;

import com.buddy.api.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Post> findPostByCategory(String gender, String region, int depth, String buddyLevel, Pageable pageable) {

        QMember member = QMember.member;
        QPost post = QPost.post;
        QPool pool = QPool.pool;
        QProfile profile = QProfile.profile;

        return jpaQueryFactory.select(post)
                .from(post)
                .join(post.member, member)
                .join(post.pool, pool)
                .join(post.member.profile, profile)
                .where(profile.gender.eq(gender))
                .where(pool.region.eq(region))
                .where(pool.depth.eq(depth))
                .where(post.buddyLevel.eq(buddyLevel))
                .orderBy(post.postDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
