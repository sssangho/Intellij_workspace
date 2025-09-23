package com.du.thymeleaf0923.controller;

import com.du.thymeleaf0923.domain.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HelloController {
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("name", "타임리프");
        return "hello";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<String> users = List.of("홍길동", "김하나", "김철수");
        model.addAttribute("users", users);
        model.addAttribute("user", "admin1");
        return "users";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("memo", new Memo());
        return "form";
    }

    @PostMapping("/form")
    public String handleForm(@ModelAttribute Memo memo) {
        System.out.println(memo.getTitle());
        return "result";
    }
}
