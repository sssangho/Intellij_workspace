package com.du.memo0924.controller;

import com.du.memo0924.domain.Memo;
import com.du.memo0924.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // 메모 목록
    @GetMapping("/memos")
    public String list(Model model) {
        model.addAttribute("memos", memoService.findAll());
        return "list"; // list.html
    }

    // 메모 작성 폼
    @GetMapping("/memos/new")
    public String createForm() {
        return "form";
    }

    // 메모 작성 처리
    @PostMapping("/memos")
    public String create(@RequestParam String title,
                         @RequestParam String content) {
        Memo memo = new Memo();
        memo.setTitle(title);
        memo.setContent(content);
        memoService.create(memo);
        return "redirect:/memos";
    }

    // 메모 상세 보기
    @GetMapping("/memos/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Memo memo = memoService.findById(id);
        model.addAttribute("memo", memo);
        return "detail";
    }

    // 메모 삭제 처리
    @PostMapping("/memos/{id}/delete")
    public String delete(@PathVariable Long id) {
        memoService.delete(id);
        return "redirect:/memos";
    }

    // 메모 수정 폼
    @GetMapping("/memos/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Memo memo = memoService.findById(id);
        model.addAttribute("memo", memo);
        return "form";
    }

    // 메모 수정 처리
    @PostMapping("/memos/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String content) {
        Memo memo = new Memo();
        memo.setId(id);
        memo.setTitle(title);
        memo.setContent(content);
        memoService.update(memo);
        return "redirect:/memos/" + id;
    }
}
