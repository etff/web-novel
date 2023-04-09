package com.example.webnovel.book.domain.book.type;

/**
 * 도서 상태.
 */
public enum BookStatus {
    /**
     * 등록, 판매 종료, 판매, 판매예정
     */
    REGISTERED, SALE_END, SALE, SCHEDULED;

    public static BookStatus from(String bookStatus) {
        if (bookStatus == null || bookStatus.isBlank()) {
            throw new IllegalArgumentException("도서 상태가 비어있으면 안됩니다.");
        }
        return BookStatus.valueOf(bookStatus);
    }
}
