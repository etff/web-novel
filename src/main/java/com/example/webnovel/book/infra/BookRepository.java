package com.example.webnovel.book.infra;

import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.type.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>, BookRepositoryCustom {
    Page<Book> findAllByBookStatus(BookStatus bookStatus, Pageable pageable);
}
