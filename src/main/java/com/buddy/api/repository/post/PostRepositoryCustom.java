package com.buddy.api.repository.post;

import com.buddy.api.domain.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findPostByCategory(String gender, String region, int depth, String buddyLevel, Pageable pageable);
}
