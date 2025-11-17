package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    // 홈
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 인증/회원
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // 상품
    @GetMapping("/products")
    public String products() {
        return "products";
    }

    // 주문(목록/체크아웃/상세)
    @GetMapping("/orders")
    public String orders() {
        return "order_list";          // orders.html (목록)
    }

    @GetMapping("/orders/checkout")
    public String ordersCheckout() {
        return "orders_checkout"; // 필요 시 템플릿 파일 추가
    }

    @GetMapping("/orders/{orderId}")
    public String orderDetail(@PathVariable Long orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "order_detail";    // 필요 시 템플릿 파일 추가
    }

    // 장바구니
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    // 즐겨찾기
    @GetMapping("/favorites")
    public String favorites() {
        return "favorites";
    }

    // 고객관리
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    //발주
    @GetMapping("/order_carts")
    public String carts(Model model) {
        model.addAttribute("message", "발주 장바구니");
        return "order_carts";
    }

    @GetMapping("/order_orderlist")
    public String ordersPage() {
        return "order_orderlist";
    }

    @GetMapping("/order_products")
    public String productsPage() {
        return "order_products";
    }
}
