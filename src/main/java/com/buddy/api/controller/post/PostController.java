package com.buddy.api.controller.post;

import com.buddy.api.controller.post.request.PostRequest;
import com.buddy.api.controller.post.response.PostResponse;
import com.buddy.api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController implements PostApiSpec{

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    public ResponseEntity findAll() {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<PostResponse> create(@AuthenticationPrincipal User user, @RequestBody PostRequest request) {
        try {
            postService.create(user.getUsername(), request);
            return ResponseEntity.ok(PostResponse.of(true, null));
        } catch (Exception e) {
            return ResponseEntity.ok(PostResponse.of(false, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity update(@AuthenticationPrincipal User user, PostRequest request) {
        return null;
    }
}
