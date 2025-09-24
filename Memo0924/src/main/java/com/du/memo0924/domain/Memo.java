package com.du.memo0924.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Memo {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
