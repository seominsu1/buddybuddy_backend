package com.buddy.api.controller.post.response;


import com.buddy.api.controller.pool.response.PoolResponse;
import com.buddy.api.controller.pool.response.PoolsResponse;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Getter
public class PostsResponse {
    private List<PostResponse> posts;
    private Integer totalElement;

    public PostsResponse(List<PostResponse> posts, Integer totalElement) {
        this.posts = posts;
        this.totalElement = totalElement;
    }

    public static PostsResponse of(List<PostResponse> posts, Integer totalElement) {
        return new PostsResponse(posts, totalElement);
    }
}
