package com.buddy.api.controller.post;

import com.buddy.api.controller.common.CommonResponse;
import com.buddy.api.controller.post.request.PostRequest;
import com.buddy.api.controller.post.response.PostResponse;
import com.buddy.api.controller.post.response.PostsResponse;
import com.buddy.api.domain.Post;
import com.buddy.api.service.PostService;
import com.buddy.api.service.dto.PostDto;
import org.springframework.data.domain.Page;
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
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> update(@PathVariable int id, @RequestBody PostRequest request) {
        try {
            postService.update(id, request);
            return ResponseEntity.ok(CommonResponse.of(true, null));
        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.of(false, e.getMessage()));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> delete(@PathVariable int id) {
        try {
            postService.delete(id);
            return ResponseEntity.ok(CommonResponse.of(true, null));
        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.of(false, e.getMessage()));
        }
    }

    @Override
    @GetMapping(value = "/paging",params = {"page"})
    public ResponseEntity<Page<PostDto>> findAllByPaging(@RequestParam("page") int page) {
        return ResponseEntity.ok(postService.findAllByPaging(page));
    }

    @Override
    @GetMapping(value = "/paging", params = {"page","gender","region","depth","buddyLevel"})
    public ResponseEntity<List<PostDto>> findByCategory(@RequestParam("page") int page,
                                                        @RequestParam("gender")  String gender,
                                                        @RequestParam("region") String region,
                                                        @RequestParam("depth") int depth,
                                                        @RequestParam("buddyLevel") String buddyLevel) {
        return ResponseEntity.ok(postService.findByCategories(page,gender,region,depth,buddyLevel));
    }

    private PostsResponse postResponses(List<Post> posts) {
        return PostsResponse.of(posts.stream()
                .map(PostResponse::of)
                .toList(), posts.size());
    }
}
