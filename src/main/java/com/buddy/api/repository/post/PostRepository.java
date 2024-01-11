package com.buddy.api.repository.post;

import com.buddy.api.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {
    @Query("select p from Post p join fetch p.member join fetch p.pool join fetch p.member.profile")
    List<Post> findPostWithMemberAndPool();

    @Query(value = "select p from Post p join fetch p.member join fetch p.pool join fetch p.member.profile")
    Page<Post> findPostAll(Pageable pageable);

//    @Query(value = "select p from Post p join fetch p.member m join fetch p.pool p1 join fetch m.profile p2 where p2.gender= :gender and where p1.region= :region and where p1.depth= :depth and where p.buddyLevel= :buddyLevel",nativeQuery = true)
//    Page<Post> findPostByCategory(@Param("gender") String gender,@Param("region") String region, @Param("depth") int depth, @Param("buddyLevel")String buddyLevel, Pageable pageable);
}
