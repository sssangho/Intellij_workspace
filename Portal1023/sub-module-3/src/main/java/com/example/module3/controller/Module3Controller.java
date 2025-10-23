package com.example.module3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.common.util.CommonUtil;

@Controller
@RequestMapping("/module3")
public class Module3Controller {

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("moduleName", "모듈 3");
        model.addAttribute("portalName", commonUtil.getPortalName());
        model.addAttribute("navigationItems", commonUtil.getNavigationItems());
        return "module3/home";
    }
    
    @GetMapping("/feature1")
    public String feature1(Model model) {
        model.addAttribute("featureName", "기능 1");
        model.addAttribute("portalName", commonUtil.getPortalName());
        model.addAttribute("moduleName", "모듈 3");
        model.addAttribute("navigationItems", commonUtil.getNavigationItems());
        return "module3/feature1";
    }
} 