package com.du.mybatis0917.controller;

import com.du.mybatis0917.dao.BoardMapper;
import com.du.mybatis0917.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private BoardMapper boardMapper;

    @GetMapping("/index")
    public String index(){
        List<Board> boards = boardMapper.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }
        return "index";
    }
}
