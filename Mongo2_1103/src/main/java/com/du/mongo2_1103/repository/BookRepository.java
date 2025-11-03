package com.du.mongo2_1103.repository;

import com.du.mongo2_1103.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}

