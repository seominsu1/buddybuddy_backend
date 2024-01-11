package com.buddy.api.service;

import com.buddy.api.controller.post.request.PostRequest;
import com.buddy.api.domain.Member;
import com.buddy.api.domain.Pool;
import com.buddy.api.domain.Post;
import com.buddy.api.exception.PostNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import com.buddy.api.repository.pool.PoolRepository;
import com.buddy.api.repository.post.PostRepository;
import com.buddy.api.service.dto.PostDto;
import jakarta.el.MethodNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return postRepository.findPostWithMemberAndPool();
    }

    public void update(int id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        Pool pool = poolRepository.findByName(request.getPoolName());
        post.updateTo(pool, request);
    }

    public void delete(int id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.updateTo(null,new PostRequest(null,null,null,null,null));
        postRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Page<PostDto> findAllByPaging(int page) {
        PageRequest pageRequest = PageRequest.of(page,9, Sort.by("postDate").descending());
        Page<Post> findPost = postRepository.findPostAll(pageRequest);
        return findPost.map(PostDto::of);
    }

    @Transactional(readOnly = true)
    public List<PostDto> findByCategories(int page, String gender, String region, int depth, String buddyLevel) {
        PageRequest pageRequest = PageRequest.of(page,9, Sort.by("postDate").descending());
        List<Post> findPost = postRepository.findPostByCategory(gender, region, depth, buddyLevel, pageRequest);
        return findPost.stream()
                .map(PostDto::of)
                .collect(Collectors.toList());
    }
}
