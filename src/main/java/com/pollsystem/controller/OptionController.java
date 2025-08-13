package com.pollsystem.controller;

import com.pollsystem.entity.Option;
import com.pollsystem.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    @GetMapping("/create/{questionId}")
    public String createOptionForm(@PathVariable Long questionId, Model model) {
        model.addAttribute("option", new Option());
        model.addAttribute("questionId", questionId);
        return "option/create";
    }

    @PostMapping("/create/{questionId}")
    public String createOption(@PathVariable Long questionId, @ModelAttribute Option option) {
        optionService.createOption(questionId, option);
        return "redirect:/polls/" + option.getQuestion().getPoll().getId();
    }

    @GetMapping("/edit/{id}")
    public String editOptionForm(@PathVariable Long id, Model model) {
        model.addAttribute("option", optionService.getOptionById(id));
        return "option/edit";
    }

    @PostMapping("/edit/{id}")
    public String editOption(@PathVariable Long id, @ModelAttribute Option option) {
        optionService.updateOption(id, option);
        return "redirect:/polls/" + option.getQuestion().getPoll().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteOption(@PathVariable Long id) {
        Long pollId = optionService.getOptionById(id).getQuestion().getPoll().getId();
        optionService.deleteOption(id);
        return "redirect:/polls/" + pollId;
    }
}
