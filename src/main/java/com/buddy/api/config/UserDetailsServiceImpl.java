package com.buddy.api.config;

import com.buddy.api.domain.Member;
import com.buddy.api.exception.MemberNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String memberId) throws RuntimeException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    log.error(memberId+"해당 사용자를 찾을 수 없습니다.");
                    return new MemberNotFoundException(memberId);
                });

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(member.getMemberId(), member.getPassword(), grantedAuthorities);
    }
}
