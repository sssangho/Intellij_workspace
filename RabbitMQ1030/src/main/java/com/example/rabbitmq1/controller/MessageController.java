package com.example.rabbitmq1.controller;

import com.example.rabbitmq1.entity.MessageEntity;
import com.example.rabbitmq1.repository.MessageRepository;
import com.example.rabbitmq1.service.MessageProducer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {

    private final MessageProducer producer;
    private final MessageRepository repository;

    public MessageController(MessageProducer producer, MessageRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<MessageEntity> messages = repository.findAll();
        model.addAttribute("messages", messages);
        return "index";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String content) {
        producer.sendMessage(content);
        return "redirect:/";
    }
}

