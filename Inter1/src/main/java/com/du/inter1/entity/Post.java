package com.du.inter1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Title;
    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    private User author;
}
