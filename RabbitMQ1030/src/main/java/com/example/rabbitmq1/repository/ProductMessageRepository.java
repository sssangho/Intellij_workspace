package com.example.rabbitmq1.repository;

import com.example.rabbitmq1.entity.ProductMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMessageRepository extends JpaRepository<ProductMessage, Long> {
    boolean existsByMessageId(String messageId);
}


