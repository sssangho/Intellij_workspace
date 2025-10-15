package com.du.security3_1015.service;

import com.du.security3_1015.entity.Board;
import com.du.security3_1015.entity.User;
import com.du.security3_1015.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 전체 게시글 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 게시글 ID로 조회
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    // 새 게시글 저장 (작성자 셋팅)
    public Board save(Board board, User author) {
        board.setAuthor(author);
        return boardRepository.save(board);
    }

    // 게시글 수정 (작성자만 가능)
    public Board update(Long id, String title, String content, User currentUser) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다. id=" + id));

        if (!board.getAuthor().getId().equals(currentUser.getId())) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        board.setTitle(title);
        board.setContent(content);
        return boardRepository.save(board);
    }

    // 게시글 삭제 (작성자만 가능)
    public void delete(Long id, User currentUser) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다. id=" + id));

        if (!board.getAuthor().getId().equals(currentUser.getId())) {
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
        }

        boardRepository.delete(board);
    }
}
