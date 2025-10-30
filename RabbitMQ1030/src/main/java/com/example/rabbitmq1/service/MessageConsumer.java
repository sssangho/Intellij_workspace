package com.example.rabbitmq1.service;

import com.example.rabbitmq1.config.RabbitConfig;
import com.example.rabbitmq1.entity.MessageEntity;
import com.example.rabbitmq1.repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final MessageRepository repository;

    public MessageConsumer(MessageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        repository.save(new MessageEntity(message));
        System.out.println("Saved message to DB: " + message);
    }
}

