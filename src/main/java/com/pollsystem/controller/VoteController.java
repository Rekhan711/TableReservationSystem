package com.pollsystem.controller;

import com.pollsystem.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/cast")
    public String castVote(@RequestParam Long optionId, @RequestParam Long pollId) {
        voteService.castVote(optionId);
        return "redirect:/polls/" + pollId;
    }
}
