package com.du.project1.controller;

import com.du.project1.entity.MyUser;
import com.du.project1.service.MyUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService service;


    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MyUser user) {
        service.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {
        MyUser user = service.login(email, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

