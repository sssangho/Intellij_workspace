package com.du.mongo2_1103.service;

import com.du.mongo2_1103.model.Book;
import com.du.mongo2_1103.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return repository.findById(id);
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public void deleteBook(String id) {
        repository.deleteById(id);
    }
}

