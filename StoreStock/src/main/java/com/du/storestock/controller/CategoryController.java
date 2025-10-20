package com.du.storestock.controller;

import com.du.storestock.entity.Category;
import com.du.storestock.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    /** 목록 */
    @GetMapping
    public String listCategories(Model model,
                                 @RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "msg", required = false) String msg) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("error", error);
        model.addAttribute("msg", msg);
        return "categories";
    }

    /** 등록 폼 */
    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    /** 저장 (신규) */
    @PostMapping("/new")
    public String addCategory(@ModelAttribute Category category, RedirectAttributes ra) {
        String name = category.getName() != null ? category.getName().trim() : "";
        if (name.isEmpty()) {
            ra.addAttribute("error", "이름을 입력해주세요.");
            return "redirect:/categories/new";
        }
        category.setName(name);
        try {
            categoryRepository.save(category);
            ra.addAttribute("msg", "카테고리를 추가했습니다.");
            return "redirect:/categories";
        } catch (DataIntegrityViolationException ex) {
            ra.addAttribute("error", "중복된 이름입니다.");
            return "redirect:/categories/new";
        }
    }

    /** 수정 폼 */
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        model.addAttribute("category", category);
        return "categoryForm";
    }

    /** 수정 처리 */
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @ModelAttribute Category form,
                                 RedirectAttributes ra) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        String name = form.getName() != null ? form.getName().trim() : "";
        if (name.isEmpty()) {
            ra.addAttribute("error", "이름을 입력해주세요.");
            return "redirect:/categories/edit/" + id;
        }
        category.setName(name);
        try {
            categoryRepository.save(category);
            ra.addAttribute("msg", "수정했습니다.");
            return "redirect:/categories";
        } catch (DataIntegrityViolationException ex) {
            ra.addAttribute("error", "중복된 이름입니다.");
            return "redirect:/categories/edit/" + id;
        }
    }

    /** 삭제 (POST) */
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes ra) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        // 상품 연관 체크 (null 안전)
        if (category.getProducts() != null && !category.getProducts().isEmpty()) {
            ra.addAttribute("error", "상품이 연결된 카테고리는 삭제할 수 없습니다.");
            return "redirect:/categories";
        }

        try {
            categoryRepository.delete(category);
            ra.addAttribute("msg", "삭제했습니다.");
        } catch (DataIntegrityViolationException ex) {
            ra.addAttribute("error", "참조 중인 데이터가 있어 삭제할 수 없습니다.");
        }
        return "redirect:/categories";
    }
}
