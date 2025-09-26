package com.du.jpa0926.controller;

import com.du.jpa0926.entity.Post;
import com.du.jpa0926.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "list";
    }

    @GetMapping("/post/new")
    public String form(Model model) {
        model.addAttribute("post", new Post());
        return "form";
    }

    @PostMapping("/post")
    public String submit(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        model.addAttribute("post", post);
        return "detail";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Post post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        model.addAttribute("post", post);
        return "form";
    }

    @PostMapping("/post/update")
    public String update(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/delete/{id}")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/";
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            Post post = Post.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            postService.save(post);
        }
    }

}
