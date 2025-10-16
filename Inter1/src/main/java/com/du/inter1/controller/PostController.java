package com.du.inter1.controller;

import com.du.inter1.entity.Post;
import com.du.inter1.entity.User;
import com.du.inter1.repository.PostRepository;
import com.du.inter1.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts/list";
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/new";
    }

    @PostMapping("/new")
    public String createPost(@ModelAttribute Post post, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User currentUser = (User) request.getSession().getAttribute("user");

        if (currentUser != null) {
            post.setAuthor(currentUser);
            postRepository.save(post);
            redirectAttributes.addFlashAttribute("message", "글이 성공적으로 작성되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "로그인 후 글을 작성할 수 있습니다.");
        }

        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id:" + id));
        User currentUser = (User) request.getSession().getAttribute("user");
        System.out.println("-------------------->"+currentUser);
        if (currentUser != null && post.getAuthor().equals(currentUser)) {
            model.addAttribute("post", post);
            return "posts/edit";
        }

        return "redirect:/posts";
    }

//    @PostMapping("/{id}/edit")
//    public String editPost(@PathVariable Long id, @ModelAttribute Post post, HttpServletRequest request, RedirectAttributes redirectAttributes) {
//        // 게시글을 조회
//        Post existingPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id:" + id));
//
//        // 세션에서 현재 사용자 정보 가져오기
//        User currentUser = (User) request.getSession().getAttribute("user");
//
//        // 현재 사용자가 게시글의 작성자인지 확인
//        if (currentUser != null && existingPost.getAuthor().getId().equals(currentUser.getId())) {
//            // 제목과 내용을 수정
//            existingPost.setTitle(post.getTitle());
//            existingPost.setContent(post.getContent());
//            postRepository.save(existingPost);
//
//            // 성공 메시지 추가
//            redirectAttributes.addFlashAttribute("message", "글이 수정되었습니다.");
//        } else {
//            // 권한이 없을 경우 오류 메시지 추가
//            redirectAttributes.addFlashAttribute("error", "권한이 없습니다.");
//        }
//
//        // 게시판 목록 페이지로 리다이렉트
//        return "redirect:/posts";
//    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, @ModelAttribute Post post, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id:" + id));
        User currentUser = (User) request.getSession().getAttribute("user");

        if (currentUser != null && existingPost.getAuthor().equals(currentUser)) {
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            postRepository.save(existingPost);
            redirectAttributes.addFlashAttribute("message", "글이 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "권한이 없습니다.");
        }

        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id:" + id));
        User currentUser = (User) request.getSession().getAttribute("user");

        if (currentUser != null && post.getAuthor().equals(currentUser)) {
            postRepository.delete(post);
            redirectAttributes.addFlashAttribute("message", "글이 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "권한이 없습니다.");
        }

        return "redirect:/posts";
    }

    // 게시글 상세보기
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        // Optional 처리
        Optional<Post> optionalPost = postRepository.findById(id);

        // 게시글이 존재하는지 확인
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "posts/view"; // 게시글 상세보기 페이지 반환
        } else {
            // 게시글이 없으면 에러 메시지 처리
            model.addAttribute("error", "게시글을 찾을 수 없습니다.");
            return "error"; // error.html로 이동
        }
    }
}

