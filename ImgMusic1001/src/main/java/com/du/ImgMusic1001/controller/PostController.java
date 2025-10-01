package com.du.ImgMusic1001.controller;

import com.du.ImgMusic1001.entitiy.SongImage;
import com.du.ImgMusic1001.entitiy.SongPost;
import com.du.ImgMusic1001.repository.SongImageRepository;
import com.du.ImgMusic1001.repository.SongPostRepository;
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
@RequestMapping("/posts")
public class PostController {

    private final SongPostRepository songPostRepository;
    private final SongImageRepository songImageRepository;

    @Value("${upload.path}") // application.properties의 경로 사용
    private String uploadPath;

    // -------------------- 게시판 목록 --------------------
    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", songPostRepository.findAll());
        return "post_list"; // JSP: post_list.jsp
    }

    // -------------------- 새 게시물 작성 폼 --------------------
    @GetMapping("/new")
    public String showPostForm(Model model) {
        model.addAttribute("post", new SongPost());
        return "post_form"; // JSP: post_form.jsp
    }

    // -------------------- 게시물 생성 --------------------
    @PostMapping
    public String createPost(@ModelAttribute SongPost songPost,
                             @RequestParam("files") MultipartFile[] files) throws IOException {

        List<SongImage> uploadedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String newFilename = uuid + "_" + originalFilename;

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                File destFile = new File(uploadDir, newFilename);
                file.transferTo(destFile);

                SongImage image = new SongImage();
                image.setFileName(originalFilename);
                image.setFilePath("/imguploads/" + newFilename);
                image.setSongPost(songPost); // 관계 설정
                uploadedImages.add(image);
            }
        }

        songPost.setImages(uploadedImages);
        songPostRepository.save(songPost); // cascade로 이미지도 함께 저장

        return "redirect:/posts";
    }

    // -------------------- 게시물 상세보기 --------------------
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        SongPost post = songPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));
        model.addAttribute("post", post);
        return "post_detail"; // JSP: post_detail.jsp
    }

    // -------------------- 게시물 삭제 --------------------
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        songPostRepository.deleteById(id);
        return "redirect:/posts";
    }

    // -------------------- 게시물 수정 폼 --------------------
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        SongPost post = songPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));
        model.addAttribute("post", post);
        return "post_form"; // post_form.jsp 재사용
    }

    // -------------------- 게시물 수정 처리 --------------------
    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Long id,
                           @ModelAttribute SongPost songPost,
                           @RequestParam("files") MultipartFile[] files) throws IOException {

        SongPost existingPost = songPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post Not Found"));

        existingPost.setTitle(songPost.getTitle());
        existingPost.setArtist(songPost.getArtist());
        existingPost.setLyrics(songPost.getLyrics());
        existingPost.setAudioPath(songPost.getAudioPath());

        List<SongImage> uploadedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String newFilename = uuid + "_" + originalFilename;

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                File destFile = new File(uploadDir, newFilename);
                file.transferTo(destFile);

                SongImage image = new SongImage();
                image.setFileName(originalFilename);
                image.setFilePath("/imguploads/" + newFilename);
                image.setSongPost(existingPost);

                uploadedImages.add(image);
            }
        }

        if (!uploadedImages.isEmpty()) {
            existingPost.getImages().clear(); // 기존 이미지 삭제
            existingPost.getImages().addAll(uploadedImages);
        }

        songPostRepository.save(existingPost);

        return "redirect:/posts/" + id;
    }
}
