package com.du.baguniee0917.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {


    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
