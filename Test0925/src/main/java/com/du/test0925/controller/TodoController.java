package com.du.test0925.controller;

import com.du.test0925.domain.Todo;
import com.du.test0925.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping
    public String list(Model model) {
        model.addAttribute("todoList", todoService.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String createForm() {
        return "form";
    }

    @PostMapping
    public String create(@RequestParam String content,
                         @RequestParam String dueDate,
                         @RequestParam String category,
                         @RequestParam(required = false) boolean completed) throws ParseException {

        Todo todo = new Todo();
        todo.setContent(content);
        todo.setDueDate(LocalDateTime.parse(dueDate));
        todo.setCategory(category);
        todo.setCompleted(completed);

        todoService.insert(todo);
        return "redirect:/todos";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @RequestParam String content,
                       @RequestParam String dueDate,
                       @RequestParam String category,
                       @RequestParam(required = false) boolean completed) throws ParseException {

        Todo todo = new Todo();
        todo.setId(id);
        todo.setContent(content);
        todo.setDueDate(LocalDateTime.parse(dueDate));
        todo.setCategory(category);
        todo.setCompleted(completed);

        todoService.update(todo);
        return "redirect:/todos/" + id;
    }

    @PostMapping("/{id}/toggle")
    public String toggleCompleted(@PathVariable Long id) {
        todoService.toggleCompleted(id);  // 서비스에서 완료 여부 반전
        return "redirect:/todos";          // 다시 목록 페이지로
    }

}

