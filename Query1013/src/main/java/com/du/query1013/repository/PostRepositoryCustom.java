package com.du.query1013.repository;

import com.du.query1013.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findByTitle(String title);
    List<Post> findByTitleContaining(String keyword);
    List<Post> findByTitleAndContent(String title, String content);
    List<Post> findByTitleContainingOrderByIdDesc(String keyword);
}

