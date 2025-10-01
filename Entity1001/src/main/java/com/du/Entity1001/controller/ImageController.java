package com.du.Entity1001.controller;

import com.du.Entity1001.entity.Image;
import com.du.Entity1001.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String listImages(Model model) {
        List<Image> images = imageRepository.findAll();
        model.addAttribute("images", images);
        return "index";
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) return "redirect:/upload";

        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newFilename = uuid + "_" + originalFilename;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs(); // 폴더 없으면 생성

        File destFile = new File(uploadDir, newFilename);
        file.transferTo(destFile);

        // DB 저장용 경로 (정적 접근 URL 기준)
        Image image = new Image();
        image.setFileName(originalFilename);
        image.setFilePath("/uploads/" + newFilename);
        imageRepository.save(image);

        return "redirect:/";
    }

    // 선택된 이미지 ID 출력 (혹은 이후 로직 처리)
    @PostMapping("/select")
    public String selectImage(@RequestParam("selectedImageId") Long selectedImagedId, Model model) {
        Image selectedImage = imageRepository.findById(selectedImagedId).orElseThrow(() -> new IllegalArgumentException("Invalid Image ID"));

        model.addAttribute("selectedImage", selectedImage);
        return "selected";
    }


}
