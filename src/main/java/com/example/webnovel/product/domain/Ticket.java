package com.example.webnovel.product.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("TICKET")
@Getter
public class Ticket extends Product {

    private Integer quantity;

    protected Ticket() {
    }

    public Ticket(String name, Integer price, Integer quantity) {
        this(null, name, price, quantity);
    }

    public Ticket(Long id, String name, Integer price, Integer quantity) {
        super(id, name, price);
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(Integer quantity) {
        if (quantity == null && quantity < 0) {
            throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
        }
    }
}
