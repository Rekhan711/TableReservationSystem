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

    @PostMapping("/create/{questionId}")
    public String createOption(@PathVariable Long questionId, @ModelAttribute Option option) {
        optionService.saveOption(option); // вместо createOption
        return "redirect:/polls/" + option.getQuestion().getPoll().getId();
    }

    @GetMapping("/edit/{id}")
    public String editOptionForm(@PathVariable Long id, Model model) {
        model.addAttribute("option", optionService.findById(id).orElseThrow());
        return "option/edit";
    }

    @PostMapping("/edit/{id}")
    public String editOption(@PathVariable Long id, @ModelAttribute Option option) {
        option.setId(id); // чтобы не потерялся ID
        optionService.saveOption(option); // вместо updateOption
        return "redirect:/polls/" + option.getQuestion().getPoll().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteOption(@PathVariable Long id) {
        Long pollId = optionService.findById(id)
                .orElseThrow()
                .getQuestion()
                .getPoll()
                .getId();
        optionService.deleteById(id);
        return "redirect:/polls/" + pollId;
    }

}
