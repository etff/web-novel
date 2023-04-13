package com.example.webnovel.product.application;

import com.example.webnovel.product.domain.Product;
import com.example.webnovel.product.domain.Ticket;
import com.example.webnovel.product.domain.type.ProductStatus;
import com.example.webnovel.product.domain.type.ProductType;
import com.example.webnovel.product.exception.ProductNotFoundException;
import com.example.webnovel.product.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TicketService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Long createProduct(String name, Integer price, Integer quantity) {
        final Ticket ticket = new Ticket(name, price, quantity);
        final Ticket saved = productRepository.save(ticket);
        return saved.getId();
    }

    @Override
    public void changeProductStatus(Long productId, ProductStatus productStatus) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        product.changeStatus(productStatus);
    }

    @Override
    public ProductType getProductType() {
        return ProductType.TICKET;
    }
}
