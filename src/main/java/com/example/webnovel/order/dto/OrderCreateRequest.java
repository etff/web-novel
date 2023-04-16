package com.example.webnovel.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {
    @NonNull
    private Long productId;
    @NonNull
    private Long userId;

    @NotBlank
    private String token;

    public OrderCreateRequest(@NonNull Long productId, @NonNull Long userId, String token) {
        this.productId = productId;
        this.userId = userId;
        this.token = token;
    }
}
