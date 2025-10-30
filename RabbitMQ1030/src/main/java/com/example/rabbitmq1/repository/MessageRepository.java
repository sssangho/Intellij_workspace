package com.example.rabbitmq1.repository;

import com.example.rabbitmq1.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {}

