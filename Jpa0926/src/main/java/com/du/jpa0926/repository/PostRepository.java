package com.du.jpa0926.repository;

import com.du.jpa0926.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
