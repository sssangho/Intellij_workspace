package com.du.Entity0930.controller;

import com.du.Entity0930.entity.Emp;
import com.du.Entity0930.service.DeptService;
import com.du.Entity0930.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emps")
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;
    private final DeptService deptService;

    @GetMapping
    public String list(Model model) {
        List<Emp> emps = empService.getAll();
        model.addAttribute("emps", emps);
        return "emp/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("emp", new Emp());
        model.addAttribute("depts", deptService.getAll());
        return "emp/create";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Emp emp) {
        empService.save(emp);
        return "redirect:/emps";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Emp emp = empService.getById(id);
        model.addAttribute("emp", emp);
        model.addAttribute("depts", deptService.getAll());
        return "emp/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute Emp emp) {
        emp.setId(id);
        empService.update(emp);
        return "redirect:/emps";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        empService.delete(id);
        return "redirect:/emps";
    }
}

