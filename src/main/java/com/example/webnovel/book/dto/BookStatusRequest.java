package com.example.webnovel.book.dto;

import com.example.webnovel.book.domain.book.type.BookStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class BookStatusRequest {
    @NonNull
    private BookStatus bookStatus;

    public BookStatusRequest(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
