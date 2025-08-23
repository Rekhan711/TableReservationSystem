package com.pollsystem.controller;

import com.pollsystem.entity.User;
import com.pollsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth") // Все URL будут начинаться с /auth
public class AuthController {

    // Инжектим UserService через конструктор (Lombok делает его сам)
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        // Джун: возвращает HTML-шаблон login.html из папки templates
        // Мидл: метод обрабатывает GET-запрос на /auth/login и показывает страницу логина
        // Сеньор: этот метод просто возвращает view name, Spring MVC сам подставит шаблон
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        // Джун: открываем страницу с формой регистрации
        // Мидл: GET /auth/register → отрисует шаблон register.html
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        // Джун: сохраняем нового пользователя
        // Мидл: User автоматически мапится из формы HTML благодаря @ModelAttribute
        // Сеньор: метод валидирует уникальность пользователя и шифрует пароль в UserService
        userService.registerUser(user);

        // После успешной регистрации делаем редирект на страницу логина
        return "redirect:/auth/login";
    }
}
