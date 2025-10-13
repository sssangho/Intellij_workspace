package com.du.query1013.util;

import com.du.query1013.entity.Post;
import com.du.query1013.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final PostRepository postRepository;

//    @PostConstruct
    public void init() {
        postRepository.save(new Post("스프링부트 시작하기", "스프링부트 입문 강좌입니다."));
        postRepository.save(new Post("JPA 기초", "Java Persistence API 설명입니다."));
        postRepository.save(new Post("타임리프 사용법", "Thymeleaf는 뷰 템플릿입니다."));
        postRepository.save(new Post("스프링과 JPA", "스프링 부트와 JPA를 함께 사용하면 강력합니다."));
        postRepository.save(new Post("테스트 글", "테스트 데이터입니다."));
    }
}
