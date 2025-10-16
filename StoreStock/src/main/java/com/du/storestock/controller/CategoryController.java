package com.du.storestock.controller;

import com.du.storestock.entity.Category;
import com.du.storestock.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    // 카테고리 목록 페이지
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    // 새 카테고리 추가
    @PostMapping("/new")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    // 카테고리 삭제
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        if (!category.getProducts().isEmpty()) {
            throw new IllegalStateException("상품과 연관된 카테고리는 삭제할 수 없습니다.");
        }
        categoryRepository.delete(category);
        return "redirect:/categories";
    }

    // 카테고리 수정 페이지 이동
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        model.addAttribute("category", category);
        return "editCategoryForm";
    }

    // 카테고리 수정 처리
    @PostMapping("/edit")
    public String updateCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}
