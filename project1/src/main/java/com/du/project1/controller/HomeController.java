package com.du.project1.controller;

import com.du.project1.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NewsService newsService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newsList", newsService.findAllNews());
        return "index";  // resources/templates/index.html
    }
}
