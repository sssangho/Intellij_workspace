package com.example.rabbitmq1.controller;

import com.example.rabbitmq1.entity.ProductMessage;
import com.example.rabbitmq1.repository.ProductMessageRepository;
import com.example.rabbitmq1.service.ProductMessageProducer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
public class ProductMessageController {

    private final ProductMessageProducer producer;
    private final ProductMessageRepository repository;

    public ProductMessageController(ProductMessageProducer producer, ProductMessageRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }

    @GetMapping("/index2")
    public String index(Model model) {
        List<ProductMessage> messages = repository.findAll();
        model.addAttribute("messages", messages);
        return "index2";
    }

    @PostMapping("/send2")
    public String sendMessage(
            @RequestParam String title,
            @RequestParam int quantity,
            @RequestParam String productCode,
            @RequestParam String content) {

        ProductMessage message = new ProductMessage(title, quantity, productCode, content);
        producer.sendMessage(message);

        return "redirect:/index2";
    }
}

