package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Board update(Long id, Board board) {
        Board existingBoard = findById(id);
        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());
        return existingBoard;
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
} 