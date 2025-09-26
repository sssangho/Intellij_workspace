package com.du.jpa0926.service;

import com.du.jpa0926.entity.Post;
import com.du.jpa0926.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
