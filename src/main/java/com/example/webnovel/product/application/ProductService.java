package com.example.webnovel.product.application;

import com.example.webnovel.product.domain.type.ProductType;

public interface ProductService {
    Long createProduct(String name, Integer price, Integer quantity);

    ProductType getProductType();
}
