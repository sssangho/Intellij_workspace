package com.du.storestock.controller;

import com.du.storestock.entity.Product;
import com.du.storestock.entity.StockHistory;
import com.du.storestock.repository.CategoryRepository;
import com.du.storestock.repository.ProductRepository;
import com.du.storestock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final StockService stockService;
    private final CategoryRepository categoryRepository;

    // 상품 목록
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    // 상품 등록 폼 페이지
    @GetMapping("/new")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("category", categoryRepository.findAll());
        return "addProductForm";
    }

    // 상품 등록 처리
    @PostMapping("/new")
    public String addProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    // 입출고 내역 조회
    @GetMapping("/{id}/history")
    public String stockHistory(@PathVariable Long id, Model model) {
        // 상품 조회
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        model.addAttribute("histories", stockService.getStockHistory(id));
        model.addAttribute("product", product);
        return "stockHistory";
    }
}
