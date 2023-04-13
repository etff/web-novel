package com.example.webnovel.product.application;

import com.example.webnovel.product.domain.type.ProductStatus;
import com.example.webnovel.product.domain.type.ProductType;

public interface ProductService {
    Long createProduct(String name, Integer price, Integer quantity);

    void changeProductStatus(Long productId, ProductStatus productStatus);

    ProductType getProductType();
}
