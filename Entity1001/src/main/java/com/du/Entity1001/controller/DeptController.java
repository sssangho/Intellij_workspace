package com.du.Entity1001.controller;

import com.du.Entity1001.entity.Dept;
import com.du.Entity1001.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/depts")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @GetMapping
    public String list(Model model) {
        List<Dept> depts = deptService.getAll();
        model.addAttribute("depts", depts);
        return "dept/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("dept", new Dept());
        return "dept/create";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Dept dept) {
        deptService.save(dept);
        return "redirect:/depts";
    }
}

