package com.du.test0925.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Todo {
    private Long id;
    private String content;
    private LocalDateTime dueDate;
    private String category;
    private boolean completed;
}
