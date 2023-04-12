package com.example.webnovel.product.infra;

import com.example.webnovel.product.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
