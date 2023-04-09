package com.example.webnovel.book.ui;

import com.example.webnovel.book.application.BookService;
import com.example.webnovel.book.dto.BookCreateRequest;
import com.example.webnovel.book.dto.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookCreateRequest request) {
        final Long bookId = bookService.createBook(
                request.getTitle(), request.getAuthorId(), request.getCategoryId());
        return ResponseEntity.created(URI.create("/books/" + bookId)).build();
    }

}
