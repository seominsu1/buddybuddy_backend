package com.buddy.api.auction.controller;

import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/render")
public class RenderController {

    @GetMapping("/{id}")
    public String getAuctionPage(@PathVariable int id, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("auctionId", id);
        model.addAttribute("masterName", "user.getName()");
        return "auction/auctionDetail";
    }
}
