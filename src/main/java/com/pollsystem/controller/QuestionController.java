package com.pollsystem.controller;

import com.pollsystem.entity.Question;
import com.pollsystem.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/create/{pollId}")
    public String createQuestionForm(@PathVariable Long pollId, Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("pollId", pollId);
        return "question/create";
    }

    @PostMapping("/create/{pollId}")
    public String createQuestion(@PathVariable Long pollId, @ModelAttribute Question question) {
        questionService.createQuestion(pollId, question);
        return "redirect:/polls/" + pollId;
    }

    @GetMapping("/edit/{id}")
    public String editQuestionForm(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.getQuestionById(id));
        return "question/edit";
    }

    @PostMapping("/edit/{id}")
    public String editQuestion(@PathVariable Long id, @ModelAttribute Question question) {
        questionService.updateQuestion(id, question);
        return "redirect:/polls/" + question.getPoll().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        Long pollId = questionService.getQuestionById(id).getPoll().getId();
        questionService.deleteQuestion(id);
        return "redirect:/polls/" + pollId;
    }
}
