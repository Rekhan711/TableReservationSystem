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
        // Джун: голосуем за выбранный вариант
        // Мидл: @RequestParam берет данные из формы (optionId и pollId)
        // Сеньор: voteService.castVote() выполняет бизнес-логику (проверки, сохранение)
        voteService.castVote(optionId);

        // После голосования → редирект обратно к опросу
        return "redirect:/polls/" + pollId;
    }
}
