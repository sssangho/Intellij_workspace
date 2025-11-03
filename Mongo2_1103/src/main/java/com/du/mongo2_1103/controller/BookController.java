package com.du.mongo2_1103.controller;

import com.du.mongo2_1103.model.Book;
import com.du.mongo2_1103.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 1️⃣ 모든 책 목록
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    // 2️⃣ 새 책 등록 폼
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    // 3️⃣ 새 책 저장
    @PostMapping
    public String createBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // 4️⃣ 책 수정 폼
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        bookService.getBookById(id)
                .ifPresent(book -> model.addAttribute("book", book));
        return "books/edit";
    }

    // 5️⃣ 책 업데이트
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable String id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // 6️⃣ 책 삭제
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    // 7️⃣ 책 상세보기
    @GetMapping("/{id}")
    public String viewBook(@PathVariable String id, Model model) {
        bookService.getBookById(id)
                .ifPresent(book -> model.addAttribute("book", book));
        return "books/view";
    }
}
