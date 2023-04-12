package com.example.webnovel.product.ui;

import com.example.webnovel.product.application.ProductService;
import com.example.webnovel.product.application.factory.ProductServiceFactory;
import com.example.webnovel.product.dto.ProductCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {
    private final ProductServiceFactory productServiceFactory;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        final ProductService productService = productServiceFactory.getProductService(request.getProductType());
        final Long productId = productService.createProduct(
                request.getName(), request.getPrice(), request.getQuantity());
        return ResponseEntity.created(URI.create("/products/" + productId)).body(productId);
    }
}
