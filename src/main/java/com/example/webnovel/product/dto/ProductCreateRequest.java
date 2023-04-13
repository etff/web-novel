package com.example.webnovel.product.dto;

import com.example.webnovel.product.domain.type.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {
    private String name;
    private Integer price;
    private Integer quantity;
    private ProductType productType;

    public ProductCreateRequest(String name, Integer price, Integer quantity, ProductType productType) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
    }
}
