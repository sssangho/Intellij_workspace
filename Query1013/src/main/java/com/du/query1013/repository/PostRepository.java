package com.du.query1013.repository;

import com.du.query1013.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    // 제목으로 검색
//    List<Post> findByTitle(String title);
//    List<Post> findByTitleContaining(String keyword);

    // 1. 제목으로 찾기
//    @Query("select p from Post p where p.title = :title") // @Query -> 객체지향 쿼리
//    List<Post> findByTitle(@Param("title") String title);

    // 2. 제목에 특정 단어가 포함된 게시글 찾기 (LIKE %keyword%)
//    @Query("select p from Post p where p.title like %:keyword%")
//    List<Post> findByTitleContaining(@Param("keyword") String keyword);
}
