package com.buddy.api.auction;

import com.buddy.api.domain.Auction;
import com.buddy.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {

    @Query("select a from Auction a join fetch a.member")
    List<Auction> findAuctionWithMember();
}
