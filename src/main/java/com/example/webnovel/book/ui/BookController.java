package com.example.webnovel.book.ui;

import com.example.webnovel.book.application.BookService;
import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.book.dto.BookCreateRequest;
import com.example.webnovel.book.dto.BookResponse;
import com.example.webnovel.book.dto.BookStatusRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookCreateRequest request) {
        final Long bookId = bookService.createBook(
                request.getTitle(), request.getAuthorId(), request.getCategoryId());
        return ResponseEntity.created(URI.create("/books/" + bookId)).build();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long bookId) {
        final BookResponse book = bookService.getBook(bookId);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getBooks(
            @RequestParam(value = "bookStatus", required = false) BookStatus bookStatus,
            Pageable pageable
    ) {
        Page<BookResponse> result = bookService.getBooks(bookStatus, pageable);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{bookId}/status")
    public ResponseEntity<Void> changeBookStatus(@PathVariable Long bookId, BookStatusRequest request) {
        bookService.updateStatus(bookId, request.getBookStatus());
        return ResponseEntity.ok().build();
    }
}
