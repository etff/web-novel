package com.example.webnovel.product.domain;

import com.example.webnovel.global.model.BaseEntity;
import com.example.webnovel.product.domain.type.ProductStatus;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private Integer price;

    @Getter
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    protected Product() {
    }

    protected Product(Long id, String name, Integer price, ProductStatus productStatus) {
        validate(name, price);
        this.id = id;
        this.name = name;
        this.price = price;
        this.productStatus = productStatus;
    }

    private void validate(String name, Integer price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품명이 비어있으면 안됩니다.");
        }
        if (price != null && price < 0) {
            throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다.");
        }
    }

    public void changeStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
