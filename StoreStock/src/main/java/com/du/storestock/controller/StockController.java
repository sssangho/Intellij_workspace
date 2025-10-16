package com.du.storestock.controller;

import com.du.storestock.entity.StockType;
import com.du.storestock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    // 입출고 입력 폼 열기
    @GetMapping("/form/{productId}")
    public String showStockForm(@PathVariable Long productId, Model model) {
        model.addAttribute("productId", productId);
        return "stockForm";
    }

    // 입출고 처리
    @PostMapping("/update")
    public String updateStock(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam StockType type,
            @RequestParam(required = false) String note
    ) {
        stockService.addStockHistory(productId, quantity, type, note);
        return "redirect:/products";
    }
}
