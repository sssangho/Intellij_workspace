package com.example.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.example.common.util.CommonUtil;

@Controller
public class PortalController {

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("portalName", commonUtil.getPortalName());
        model.addAttribute("version", commonUtil.getVersion());
        model.addAttribute("navigationItems", commonUtil.getNavigationItems());
        return "portal/home";
    }
    
    // 직접 리디렉션이 필요한 경우를 위한 메서드 유지
    @GetMapping("/redirect/module1")
    public RedirectView redirectModule1() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/module1/");
        return redirectView;
    }
    
    @GetMapping("/redirect/module2")
    public RedirectView redirectModule2() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8082/module2/");
        return redirectView;
    }
} 