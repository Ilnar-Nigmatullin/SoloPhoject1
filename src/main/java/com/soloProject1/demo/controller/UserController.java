package com.soloProject1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/403")
    public String errorPage() {
        return "403";
    }
}
