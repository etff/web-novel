package com.example.webnovel.book.infra;

import com.example.webnovel.book.domain.book.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
