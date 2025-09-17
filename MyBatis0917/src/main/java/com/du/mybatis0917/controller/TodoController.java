package com.du.mybatis0917.controller;

import com.du.mybatis0917.dao.TodoMapper;
import com.du.mybatis0917.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TodoController {

    private final TodoMapper todoDao;
    public TodoController(TodoMapper todoDao) {
        this.todoDao = todoDao;
    }

//    private final TodoDao todoDao;
//    public TodoController(TodoDao todoDao) {
//        this.todoDao = todoDao;
//    }
    
    @GetMapping("/")
    public String listTodos(Model model) {
        List<Todo> todos = todoDao.findAll();
        model.addAttribute("todos", todos);
        return "list";
    }
    
    @GetMapping("/add")
    public String addForm() {
        return "add";
    }
    
    @PostMapping("/add")
    public String addTodo(@RequestParam("title") String title) {
        todoDao.add(title);
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id) {
        todoDao.delete(id);
        return "redirect:/";
    }
    
    @GetMapping("/toggle/{id}")
    public String toggleCompleted(@PathVariable int id) {
        todoDao.toggleCompleted(id);
        return "redirect:/";
    }
}




