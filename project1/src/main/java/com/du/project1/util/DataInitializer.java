package com.du.project1.util;

import com.du.project1.entity.News;
import com.du.project1.repository.NewsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final NewsRepository repo;

//    @PostConstruct
    public void initData() {
        repo.save(News.builder()
                .title("최신 뉴스 1")
                .content("최신 뉴스 내용입니다.")
                .image("news1.jpg")
                .build());

        repo.save(News.builder()
                .title("최신 뉴스 2")
                .content("두 번째 뉴스입니다.")
                .image("news2.jpg")
                .build());

        repo.save(News.builder()
                .title("최신 뉴스 3")
                .content("세 번째 뉴스입니다.")
                .image("news3.jpg")
                .build());
    }
}
