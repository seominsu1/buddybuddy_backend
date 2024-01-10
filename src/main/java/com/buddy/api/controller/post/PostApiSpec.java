package com.buddy.api.controller.post;

import com.buddy.api.controller.post.request.PostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface PostApiSpec {

    ResponseEntity findAll();
    ResponseEntity create(User user, PostRequest request);
    ResponseEntity update(int postId, PostRequest request);
    ResponseEntity delete(int postId);
    ResponseEntity findAllByPaging(int page);
}
