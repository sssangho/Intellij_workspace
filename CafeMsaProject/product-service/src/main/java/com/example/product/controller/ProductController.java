package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 전체 조회
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 생성
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(null); // 안전하게
        return productRepository.save(product);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody Product update) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setName(update.getName());
                    p.setPrice(update.getPrice());
                    p.setStock(update.getStock());
                    p.setCategory(update.getCategory());
                    // 필요하면 size, description 등도 같이 업데이트
                    return ResponseEntity.ok(productRepository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
