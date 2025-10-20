package com.du.storestock.controller;

import com.du.storestock.entity.Category;
import com.du.storestock.entity.Product;
import com.du.storestock.entity.StockHistory;
import com.du.storestock.entity.StockType;
import com.du.storestock.repository.CategoryRepository;
import com.du.storestock.repository.ProductRepository;
import com.du.storestock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockService stockService;

    // 상품 목록
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // 상품 등록 폼
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "productForm";
    }

    // 상품 수정 폼
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "productForm";
    }

    // 상품 저장 (신규)
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam(required = false) Long categoryId) {

        // 카테고리 세팅
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
            product.setCategory(category);
        }

        // 재고 null 방지
        if (product.getStock_quantity() == null) {
            product.setStock_quantity(0);
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    // 상품 수정 처리
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute Product updatedProduct,
                                @RequestParam(required = false) Long categoryId) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStock_quantity(
                updatedProduct.getStock_quantity() == null ? 0 : updatedProduct.getStock_quantity()
        );
        product.setImageUrl(updatedProduct.getImageUrl());

        // 카테고리 세팅
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
            product.setCategory(category);
        } else {
            product.setCategory(null); // 선택 해제 허용하려면 유지, 아니면 기존 값 유지 로직으로 변경
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    // 입출고 내역 조회
    @GetMapping("/{id}/history")
    public String stockHistory(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        model.addAttribute("product", product);
        model.addAttribute("histories", stockService.getStockHistory(id));
        return "stockHistory";
    }

    // (추가) 입/출고 등록
    @PostMapping("/{id}/history")
    public String addStockHistory(@PathVariable Long id,
                                  @RequestParam StockType type,
                                  @RequestParam int quantity,
                                  @RequestParam(required = false) String note) {
        stockService.addStockHistory(id, quantity, type, note);
        return "redirect:/products/" + id + "/history";
    }

    // 상품 삭제
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        productRepository.delete(product); // StockHistory는 cascade로 함께 삭제
        return "redirect:/products";
    }
}
