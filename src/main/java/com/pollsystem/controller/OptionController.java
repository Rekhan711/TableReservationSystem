package com.pollsystem.controller;

import com.pollsystem.entity.Option;
import com.pollsystem.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/options") // Все методы внутри будут работать с URL /options/...
public class OptionController {

    private final OptionService optionService;

    @PostMapping("/create/{questionId}")
    public String createOption(@PathVariable Long questionId, @ModelAttribute Option option) {
        // Джун: сохраняем вариант ответа (option) для вопроса (questionId)
        // Мидл: @PathVariable → достает questionId из URL, @ModelAttribute → достает Option из формы
        // Сеньор: после сохранения делаем редирект обратно к опросу, чтобы обновить UI
        optionService.saveOption(option);
        return "redirect:/polls/" + option.getQuestion().getPoll().getId();
    }

    @GetMapping("/edit/{id}")
    public String editOptionForm(@PathVariable Long id, Model model) {
        // Джун: достаем вариант ответа по id и передаем его в модель
        // Мидл: модель пробрасывается в шаблон option/edit.html
        // Сеньор: если опция не найдена, бросается исключение
        model.addAttribute("option", optionService.findById(id).orElseThrow());
        return "option/edit";
    }

    @PostMapping("/edit/{id}")
    public String editOption(@PathVariable Long id, @ModelAttribute Option option) {
        // Джун: обновляем вариант ответа
        // Мидл: setId гарантирует, что будет обновление, а не вставка нового объекта
        // Сеньор: сохранение делегируется сервису
        option.setId(id);
        optionService.saveOption(option);
        return "redirect:/polls/" + option.getQuestion().getPoll().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteOption(@PathVariable Long id) {
        // Джун: удаляем вариант ответа
        // Мидл: сначала достаем pollId → нужно для редиректа
        // Сеньор: без этого не получится вернуться на страницу опроса
        Long pollId = optionService.findById(id)
                .orElseThrow()
                .getQuestion()
                .getPoll()
                .getId();
        optionService.deleteById(id);
        return "redirect:/polls/" + pollId;
    }
}
