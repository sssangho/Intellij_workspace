package com.du.query1013.repository;

import com.du.query1013.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    // 1. 제목으로 찾기
    @Override
    public List<Post> findByTitle(String title) {
        String jpql = "SELECT p FROM Post p WHERE p.title = :title";
        return em.createQuery(jpql, Post.class)
                .setParameter("title", title)
                .getResultList();
    }

    // 2. 제목에 특정 단어가 포함된 게시글 찾기 (LIKE %keyword%)
    @Override
    public List<Post> findByTitleContaining(String keyword) {
        String jpql = "SELECT p FROM Post p WHERE p.title LIKE :keyword";
        return em.createQuery(jpql, Post.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    // 3. 제목과 내용으로 찾기 (AND 조건)
    @Override
    public List<Post> findByTitleAndContent(String title, String content) {
        String jpql = "SELECT p FROM Post p WHERE p.title = :title AND p.content = :content";
        return em.createQuery(jpql, Post.class)
                .setParameter("title", title)
                .setParameter("content", content)
                .getResultList();
    }

    // 4. 제목에 특정 단어가 포함되고 ID 내림차순 정렬
    @Override
    public List<Post> findByTitleContainingOrderByIdDesc(String keyword) {
        String jpql = "SELECT p FROM Post p WHERE p.title LIKE :keyword ORDER BY p.id DESC";
        return em.createQuery(jpql, Post.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
}
