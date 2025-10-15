package com.du.security3_1015.controller;

import com.du.security3_1015.dto.BoardDto;
import com.du.security3_1015.entity.Board;
import com.du.security3_1015.entity.User;
import com.du.security3_1015.service.BoardService;
import com.du.security3_1015.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    // 게시글 목록
    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "boards/list";
    }

    // 게시글 작성 폼
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "boards/new";
    }

    // 게시글 작성 처리
    @PostMapping
    public String create(@ModelAttribute BoardDto boardDto,
                         @AuthenticationPrincipal UserDetails userDetails) {
        User author = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardService.save(board, author);

        return "redirect:/boards";
    }

    // 게시글 상세
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model,
                         @AuthenticationPrincipal UserDetails userDetails) {
        Board board = boardService.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다. id=" + id));
        model.addAttribute("board", board);

        // 작성자와 로그인 사용자 비교용
        boolean isAuthor = userDetails != null && board.getAuthor().getUsername().equals(userDetails.getUsername());
        model.addAttribute("isAuthor", isAuthor);

        return "boards/detail";
    }

    // 게시글 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model,
                           @AuthenticationPrincipal UserDetails userDetails) {
        Board board = boardService.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다. id=" + id));

        if (!board.getAuthor().getUsername().equals(userDetails.getUsername())) {
            return "redirect:/boards/" + id;
        }

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());

        model.addAttribute("board", boardDto);
        model.addAttribute("boardId", id);
        return "boards/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute BoardDto boardDto,
                         @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        boardService.update(id, boardDto.getTitle(), boardDto.getContent(), currentUser);
        return "redirect:/boards/" + id;
    }

    // 게시글 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        boardService.delete(id, currentUser);
        return "redirect:/boards";
    }
}

