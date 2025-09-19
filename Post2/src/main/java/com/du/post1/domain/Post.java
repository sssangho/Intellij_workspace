package com.du.post1.domain;

import lombok.Data;

@Data
public class Post {
    private Long id;
    private String title;
    private String content;
    private String writer;
}
