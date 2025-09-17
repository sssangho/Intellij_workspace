package com.du.mybatis0917.controller;

import com.du.mybatis0917.dao.BoardMapper;
import com.du.mybatis0917.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardMapper boardMapper;

    public BoardController(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @GetMapping
    String showBoard(@RequestParam(required = false) Integer editId, Model model) {
        System.out.println(editId);
        Board editTarget = (editId != null) ? boardMapper.findById(editId) : new Board();
        List<Board> list = boardMapper.findAll();
        model.addAttribute("list", list);
        model.addAttribute("form", editTarget);
        return "board";
    }

    @PostMapping("/save")
    String save(Board board) {
        if (board.getId() == null) {
            boardMapper.insert(board);
        } else {
            boardMapper.update(board);
        }
        return "redirect:/board";
    }

    @GetMapping("/delete/{id}")
    String delete(@PathVariable int id) {
        boardMapper.delete(id);
        return "redirect:/board";
    }

}
