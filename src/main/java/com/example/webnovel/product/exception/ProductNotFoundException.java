package com.example.webnovel.product.exception;

import com.example.webnovel.global.error.exception.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(Long target) {
        super(target + " is not found");
    }
}
