package com.soloProject1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    /*Страница логина*/
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
