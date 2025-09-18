package com.du.baguniee0917.controller;

import com.du.baguniee0917.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", shopService.getProducts());
        return "product_list";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartItems", shopService.getCartItems());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        shopService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String removeFromCart(@RequestParam Long cartItemId) {
        shopService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
}
