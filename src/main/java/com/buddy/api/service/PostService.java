package com.buddy.api.service;

import com.buddy.api.controller.post.request.PostRequest;
import com.buddy.api.domain.Member;
import com.buddy.api.domain.Pool;
import com.buddy.api.domain.Post;
import com.buddy.api.repository.member.MemberRepository;
import com.buddy.api.repository.pool.PoolRepository;
import com.buddy.api.repository.post.PostRepository;
import jakarta.el.MethodNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PoolRepository poolRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository, PoolRepository poolRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.poolRepository = poolRepository;
    }

    public void create(String memberId, PostRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(MethodNotFoundException::new);
        Pool pool = poolRepository.findByName(request.getPoolName());
        postRepository.save(Post.of(member, pool, request.getDate(), request.getBuddyLevel(), request.getContent(), request.getOpenTalkUrl()));
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
