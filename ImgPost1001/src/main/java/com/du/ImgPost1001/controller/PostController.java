package com.du.ImgPost1001.controller;

import com.du.ImgPost1001.entity.Image;
import com.du.ImgPost1001.entity.Post;
import com.du.ImgPost1001.repository.ImageRepository;
import com.du.ImgPost1001.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/posts/new")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post_form";
    }

    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post,
                             @RequestParam("files") MultipartFile[] files) throws IOException {

        List<Image> uploadedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String newFilename = uuid + "_" + originalFilename;

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                File destFile = new File(uploadDir, newFilename);
                file.transferTo(destFile);

                Image image = new Image();
                image.setFileName(originalFilename);
                image.setFilePath("/uploads/" + newFilename);
                image.setPost(post);  // 관계 설정

                uploadedImages.add(image);
            }
        }

        post.setImages(uploadedImages);
        postRepository.save(post);  // cascade로 이미지도 함께 저장됨

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "post_list";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        model.addAttribute("post", post);
        return "post_detail";
    }
}

