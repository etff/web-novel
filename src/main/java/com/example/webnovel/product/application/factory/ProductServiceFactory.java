package com.example.webnovel.product.application.factory;

import com.example.webnovel.product.application.ProductService;
import com.example.webnovel.product.domain.type.ProductType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceFactory {

    private final Map<ProductType, ProductService> productServiceMap = new HashMap<>();

    /**
     * 생성자주입
     *
     * @param productServices
     */
    public ProductServiceFactory(List<ProductService> productServices) {
        productServices.forEach(it -> productServiceMap.put(it.getProductType(), it));
    }

    public ProductService getProductService(ProductType productType) {
        return productServiceMap.get(productType);
    }
}
