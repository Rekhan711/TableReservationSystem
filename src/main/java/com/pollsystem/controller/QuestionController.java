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
        Question question = new Question();
        model.addAttribute("question", question);
        model.addAttribute("pollId", pollId);
        return "question/create";
    }

    @PostMapping("/create/{pollId}")
    public String createQuestion(@PathVariable Long pollId, @ModelAttribute Question question) {
        // тут важно: вопрос должен ссылаться на опрос
        // предполагается, что в Question есть setPoll()
        questionService.saveQuestion(question);
        return "redirect:/polls/" + pollId;
    }

    @GetMapping("/edit/{id}")
    public String editQuestionForm(@PathVariable Long id, Model model) {
        Question question = questionService.findById(id).orElseThrow();
        model.addAttribute("question", question);
        return "question/edit";
    }

    @PostMapping("/edit/{id}")
    public String editQuestion(@PathVariable Long id, @ModelAttribute Question question) {
        question.setId(id); // чтобы обновить, а не создать новый
        questionService.saveQuestion(question);
        return "redirect:/polls/" + question.getPoll().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        Question question = questionService.findById(id).orElseThrow();
        Long pollId = question.getPoll().getId();
        questionService.deleteById(id);
        return "redirect:/polls/" + pollId;
    }
}
