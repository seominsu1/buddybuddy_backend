package com.buddy.api.auction.controller;

import com.buddy.api.auction.*;
import com.buddy.api.auction.request.AuctionRequest;
import com.buddy.api.auction.response.AuctionResponse;
import com.buddy.api.auction.response.AuctionsResponse;
import com.buddy.api.auction.service.AuctionService;
import com.buddy.api.controller.common.CommonResponse;
import com.buddy.api.domain.Auction;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class AuctionController {

    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @MessageMapping("/{auctionId}") // 여기로 전송되면 메소드 호출
    @SendTo("/auctionRoom/{auctionId}")
    public BidResponse greeting(@DestinationVariable int auctionId, BidMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        //받은 경매가 이벤트 처리
        String resultMessage = auctionService.bidAuction(auctionId, HtmlUtils.htmlEscape(message.getMasterName()), HtmlUtils.htmlEscape(message.getPrice()));
        return new BidResponse(resultMessage);
    }

    @PostMapping("/api/v1/auction")
    public ResponseEntity<CommonResponse> create(@AuthenticationPrincipal User user, @RequestBody AuctionRequest request) {
        try {
            auctionService.create(user.getUsername(), request);
            return ResponseEntity.ok(CommonResponse.of(true, null));
        } catch (Exception e) {
            return ResponseEntity.ok(CommonResponse.of(false, e.getMessage()));
        }
    }

    @GetMapping("/api/v1/auction")
    public ResponseEntity<AuctionsResponse> findAll() {
        return ResponseEntity.ok(auctionResponses(auctionService.findAll()));
    }

    private AuctionsResponse auctionResponses(List<Auction> auctions) {
        return AuctionsResponse.of(auctions.stream()
                .map(AuctionResponse::of)
                .toList(), auctions.size());
    }
}
