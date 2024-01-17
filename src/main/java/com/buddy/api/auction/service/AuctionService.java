package com.buddy.api.auction.service;

import com.buddy.api.auction.AuctionRepository;
import com.buddy.api.auction.request.AuctionRequest;
import com.buddy.api.domain.Auction;
import com.buddy.api.domain.Member;
import com.buddy.api.repository.member.MemberRepository;
import jakarta.el.MethodNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final MemberRepository memberRepository;

    public AuctionService(AuctionRepository auctionRepository, MemberRepository memberRepository) {
        this.auctionRepository = auctionRepository;
        this.memberRepository = memberRepository;
    }

    public void create(String memberId, AuctionRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(MethodNotFoundException::new);
        auctionRepository.save(Auction.of(member,request.getTitle(),request.getContent(),request.getBidEndDate()));
    }

    @Transactional(readOnly = true)
    public List<Auction> findAll() {
        return auctionRepository.findAuctionWithMember();
    }
}
