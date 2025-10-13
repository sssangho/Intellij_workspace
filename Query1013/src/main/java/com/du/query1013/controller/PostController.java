package com.du.query1013.controller;

import com.du.query1013.entity.Post;
import com.du.query1013.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Post> posts = (keyword != null && !keyword.isEmpty()) ? postRepository.findByTitleContaining(keyword) : postRepository.findAll();
        model.addAttribute("posts", posts);
        return "list";
    }

    @GetMapping("/new")
    public String from(Model model) {
        model.addAttribute("post", new Post());
        return "form";
    }

    @PostMapping
    public String save(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    }
}
