package com.pollsystem.controller;

import com.pollsystem.entity.Poll;
import com.pollsystem.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/polls")
public class PollController {

    private final PollService pollService;

    @GetMapping
    public String listPolls(Model model) {
        model.addAttribute("polls", pollService.getAllPolls());
        return "poll/list";
    }

    @GetMapping("/create")
    public String createPollForm(Model model) {
        model.addAttribute("poll", new Poll());
        return "poll/create";
    }

    @PostMapping
    public String createPoll(@ModelAttribute Poll poll) {
        pollService.createPoll(poll);
        return "redirect:/polls";
    }

    @GetMapping("/{id}")
    public String viewPoll(@PathVariable Long id, Model model) {
        model.addAttribute("poll", pollService.getPollById(id));
        return "poll/view";
    }
}
