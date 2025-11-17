package com.example.order_cart.controller;

import com.example.order_cart.dto.Order_ProductInfo;
import com.example.order_cart.model.Order_CartItem;
import com.example.order_cart.repository.Order_CartItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/order_carts")
public class Order_CartController {

    private final Order_CartItemRepository cartItemRepository;

    public Order_CartController(Order_CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    // ✅ 장바구니 전체 조회
    @GetMapping
    public List<Order_CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }

    // ✅ 장바구니 항목 추가 (JS에서 호출하는 POST /api/carts)
    @PostMapping("/{productId}")
    public ResponseEntity<Order_CartItem> addItemByProductId(
            @PathVariable Long productId,
            @RequestBody(required = false) Order_CartItem requestItem // 👈 수량 받아오기
    ) {
        int quantity = (requestItem != null && requestItem.getQuantity() > 0)
                ? requestItem.getQuantity()
                : 1; // 기본값 1

        Order_CartItem existing = cartItemRepository.findAll().stream()
                .filter(i -> productId.equals(i.getProductId()))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            return ResponseEntity.ok(cartItemRepository.save(existing));
        }

        // product-service에서 상품 정보 가져오기
        RestTemplate restTemplate = new RestTemplate();
        Order_ProductInfo product = restTemplate.getForObject(
                "http://localhost:8000/api/order_products/" + productId,
                Order_ProductInfo.class
        );

        Order_CartItem newItem = new Order_CartItem();
        newItem.setProductId(product.getId());
        newItem.setProductName(product.getName());
        newItem.setPrice(product.getPrice());
        newItem.setQuantity(quantity); // ✅ 여기 반영

        return ResponseEntity.ok(cartItemRepository.save(newItem));
    }




    // ✅ 장바구니 항목 수정
    @PutMapping("/{id}")
    public ResponseEntity<Order_CartItem> updateItem(
            @PathVariable Long id,
            @RequestBody Order_CartItem updatedItem) {

        return cartItemRepository.findById(id)
                .map(item -> {
                    item.setProductId(updatedItem.getProductId());
                    item.setProductName(updatedItem.getProductName());
                    item.setQuantity(updatedItem.getQuantity());
                    item.setPrice(updatedItem.getPrice());
                    return ResponseEntity.ok(cartItemRepository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 장바구니 항목 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!cartItemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cartItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ 장바구니 전체 삭제
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllItems() {
        cartItemRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
