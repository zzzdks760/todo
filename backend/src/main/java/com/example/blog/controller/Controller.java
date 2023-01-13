package com.example.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller

public class Controller {
    @GetMapping("/vue")
    public String vue() {
        return "vue/index";
    }
}
