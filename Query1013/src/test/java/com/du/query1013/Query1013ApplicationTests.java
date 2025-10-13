package com.du.query1013;

import com.du.query1013.entity.Post;
import com.du.query1013.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Query1013ApplicationTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    void contextLoads() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    void findByTitle() {
        List<Post> posts = postRepository.findByTitleContaining("스프링");
        for (Post post : posts) {
            System.out.println(post);
        }
    }

}
