package com.du.mongo2_1103.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String title;   // 책 제목
    private String author;  // 저자
    private String publisher; // 출판사 추가 가능
    private double price;   // 가격 (소수점 가능)
}
