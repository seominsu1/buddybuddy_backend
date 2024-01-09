package com.buddy.api.controller.post;

import com.buddy.api.controller.common.CommonResponse;
import com.buddy.api.controller.pool.response.PoolResponse;
import com.buddy.api.controller.pool.response.PoolsResponse;
import com.buddy.api.controller.post.request.PostRequest;
import com.buddy.api.controller.post.response.PostResponse;
import com.buddy.api.controller.post.response.PostsResponse;
import com.buddy.api.domain.Pool;
import com.buddy.api.domain.Post;
import com.buddy.api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController implements PostApiSpec{

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    @GetMapping
    public ResponseEntity<PostsResponse> findAll() {
        return ResponseEntity.ok(postResponses(postService.findAll()));
    }

    @Override
    @PostMapping
    public ResponseEntity<CommonResponse> create(@AuthenticationPrincipal User user, @RequestBody PostRequest request) {
        try {
            postService.create(user.getUsername(), request);
            return ResponseEntity.ok(CommonResponse.of(true, null));
        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.of(false, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<CommonResponse> update(@AuthenticationPrincipal User user, PostRequest request) {
        return null;
    }

    private PostsResponse postResponses(List<Post> posts) {
        return PostsResponse.of(posts.stream()
                .map(PostResponse::of)
                .toList(), posts.size());
    }
}
