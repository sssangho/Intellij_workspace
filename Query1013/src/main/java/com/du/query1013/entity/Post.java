package com.du.query1013.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    private String title;

    @Column(length = 1000)
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
