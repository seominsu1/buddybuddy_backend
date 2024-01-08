package com.buddy.api.repository.post;

import com.buddy.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("select p from Post p join fetch p.member join fetch p.pool join fetch p.member.profile")
    List<Post> findPostWithMemberAndPool();
}
