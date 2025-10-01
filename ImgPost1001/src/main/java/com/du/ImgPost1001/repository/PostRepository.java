package com.du.ImgPost1001.repository;

import com.du.ImgPost1001.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 기본 CRUD + 필요 시 커스텀 메서드 추가 가능
}

