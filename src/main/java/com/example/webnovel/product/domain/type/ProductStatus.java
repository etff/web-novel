package com.example.webnovel.product.domain.type;

public enum ProductStatus {
    /**
     * 등록, 판매 종료, 판매, 판매예정
     */
    REGISTERED, SALE_END, SALE, SOLD_OUT, SCHEDULED;

    public static ProductStatus from(String productStatus) {
        if (productStatus == null || productStatus.isBlank()) {
            throw new IllegalArgumentException("도서 상태가 비어있으면 안됩니다.");
        }
        return ProductStatus.valueOf(productStatus);
    }
}
