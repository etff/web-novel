package com.example.webnovel.product.dto;

import com.example.webnovel.product.domain.type.ProductStatus;
import com.example.webnovel.product.domain.type.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class ProductStatusRequest {
    @NonNull
    private ProductStatus productStatus;

    @NonNull
    private ProductType productType;

    public ProductStatusRequest(ProductStatus productStatus, ProductType productType) {
        this.productStatus = productStatus;
        this.productType = productType;
    }
}
