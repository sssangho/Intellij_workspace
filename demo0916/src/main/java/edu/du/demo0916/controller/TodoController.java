package edu.du.demo0916.controller;

import java.util.List;

import edu.du.demo0916.dao.TodoDao;
import edu.du.demo0916.dao.TodoMapper;
import edu.du.demo0916.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    @Autowired
    private TodoMapper todoMapper;

	private final TodoDao todoDao;
	
	public TodoController(TodoDao todoDao) {
		this.todoDao =todoDao;
	}
	
	@GetMapping("/")
	public String listTodos(Model model) {
//		List<Todo> todos = todoDao.findAll();
        List<Todo> todos = todoMapper.findAll();
		model.addAttribute("todos", todos);
		return "list";
	}
	
	@GetMapping("/add") // 폼을 띄워준다.
	public String addForm() {
		return "add";
	}
	
	@PostMapping("/add") // insert
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
