package com.example.Birthdays.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeftController {
    @GetMapping("/left-list")
        public String leftList() {
        return "left-list";
    }

    @GetMapping("/home")
    public String imHome() {
        return "redirect:/";
    }
}
