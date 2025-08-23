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
        // Джун: создаем новый вопрос
        // Мидл: пробрасываем pollId, чтобы знать, к какому опросу он относится
        // Сеньор: UI должен отрендерить форму question/create.html
        Question question = new Question();
        model.addAttribute("question", question);
        model.addAttribute("pollId", pollId);
        return "question/create";
    }

    @PostMapping("/create/{pollId}")
    public String createQuestion(@PathVariable Long pollId, @ModelAttribute Question question) {
        // Джун: сохраняем вопрос
        // Мидл: нужно привязать вопрос к опросу через setPoll()
        // Сеньор: потом делаем редирект на страницу опроса
        questionService.saveQuestion(question);
        return "redirect:/polls/" + pollId;
    }

    @GetMapping("/edit/{id}")
    public String editQuestionForm(@PathVariable Long id, Model model) {
        // Джун: достаем вопрос по ID и показываем в форме
        // Мидл: если не найден → исключение
        // Сеньор: шаблон question/edit.html использует объект question
        Question question = questionService.findById(id).orElseThrow();
        model.addAttribute("question", question);
        return "question/edit";
    }

    @PostMapping("/edit/{id}")
    public String editQuestion(@PathVariable Long id, @ModelAttribute Question question) {
        // Джун: обновляем вопрос
        // Мидл: setId гарантирует, что это update, а не insert
        // Сеньор: сохраняем и редиректим обратно к опросу
        question.setId(id);
        questionService.saveQuestion(question);
        return "redirect:/polls/" + question.getPoll().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        // Джун: удаляем вопрос
        // Мидл: достаем pollId → нужно для редиректа
        // Сеньор: после удаления редиректим обратно в опрос
        Question question = questionService.findById(id).orElseThrow();
        Long pollId = question.getPoll().getId();
        questionService.deleteById(id);
        return "redirect:/polls/" + pollId;
    }
}
