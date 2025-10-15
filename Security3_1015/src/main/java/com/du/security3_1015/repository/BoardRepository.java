package com.du.security3_1015.repository;

import com.du.security3_1015.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByAuthorUsername(String username);
}
