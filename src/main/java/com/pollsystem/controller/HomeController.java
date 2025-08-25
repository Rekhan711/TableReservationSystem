package com.pollsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // корневой URL
    public String home() {
        return "index"; // вернёт шаблон index.html из resources/templates
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // вернёт login.html
    }
}
