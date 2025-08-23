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
        // Джун: достаем все опросы и передаем их в шаблон
        // Мидл: pollService.findAll() возвращает список Poll
        // Сеньор: MVC-связка → список будет доступен как ${polls} в poll/list.html
        model.addAttribute("polls", pollService.findAll());
        return "poll/list";
    }

    @GetMapping("/create")
    public String createPollForm(Model model) {
        // Джун: открываем форму создания опроса
        // Мидл: кладем пустой Poll в модель для биндинга формы
        model.addAttribute("poll", new Poll());
        return "poll/create";
    }

    @PostMapping
    public String createPoll(@ModelAttribute Poll poll) {
        // Джун: сохраняем опрос
        // Мидл: Poll автоматически мапится из формы HTML
        // Сеньор: логика сохранения в сервисе
        pollService.savePoll(poll);
        return "redirect:/polls";
    }

    @GetMapping("/{id}")
    public String viewPoll(@PathVariable Long id, Model model) {
        // Джун: показываем опрос по ID
        // Мидл: если опроса нет, будет исключение
        // Сеньор: poll передается в шаблон poll/view.html
        model.addAttribute("poll", pollService.findById(id).orElseThrow());
        return "poll/view";
    }
}
