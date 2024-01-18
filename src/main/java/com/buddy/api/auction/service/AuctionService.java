package com.buddy.api.auction.service;

import com.buddy.api.auction.AuctionRepository;
import com.buddy.api.auction.BidMessage;
import com.buddy.api.auction.MessageBuilder;
import com.buddy.api.auction.request.AuctionRequest;
import com.buddy.api.domain.Auction;
import com.buddy.api.domain.Member;
import com.buddy.api.exception.AuctionNotFoundException;
import com.buddy.api.repository.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.el.MethodNotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuctionService {
    private static final String REDIS_AUCTION_PREFIX = "auction";
    private final AuctionRepository auctionRepository;
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public AuctionService(AuctionRepository auctionRepository, MemberRepository memberRepository, RedisTemplate<String, String> redisTemplate) {
        this.auctionRepository = auctionRepository;
        this.memberRepository = memberRepository;
        this.redisTemplate = redisTemplate;
    }

    public void create(String memberId, AuctionRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(MethodNotFoundException::new);
        auctionRepository.save(Auction.of(member,request.getTitle(),request.getContent(),request.getBidEndDate()));
    }

    @Transactional(readOnly = true)
    public List<Auction> findAll() {
        return auctionRepository.findAuctionWithMember();
    }

    public String bidAuction(int auctionId, String bidder, String price) {
        // redis에 있는 현재 경매가와 비교
        return compareBid(auctionId, bidder, price);
    }

    private String compareBid(int auctionId, String bidder, String price) {
        BidMessage bidMessage = new BidMessage(bidder, price);
        MessageBuilder mb = new MessageBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String value;
        try {
            value = mapper.writeValueAsString(bidMessage);
        } catch (Exception e) {
            return "[error]";
        }
        if (Boolean.FALSE.equals(redisTemplate.opsForValue().setIfAbsent(REDIS_AUCTION_PREFIX + auctionId, value))) {
            BidMessage originBid;
            try {
                 originBid = mapper.readValue(redisTemplate.opsForValue().get(REDIS_AUCTION_PREFIX + auctionId), BidMessage.class);
            } catch (Exception e) {
                return "[error]";
            }
            if (Integer.parseInt(price) > Integer.parseInt(originBid.getPrice())) {
                redisTemplate.opsForValue().set(REDIS_AUCTION_PREFIX + auctionId, value);
                return mb.messageBuild(true, bidder, price);
            }
            return mb.messageBuild(false, originBid.getMasterName(), originBid.getPrice());
        }
        return mb.messageBuild(true, bidder, price);
    }
}
