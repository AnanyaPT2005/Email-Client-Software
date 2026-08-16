package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String showForm() {
        return "email-form";  
        // returns email-form.html from templates folder
    }

    // @GetMapping("/api/v1/emails/send-with-file")
    // public String showEmailForm() {
    //     return "email-form";
    // }
}
