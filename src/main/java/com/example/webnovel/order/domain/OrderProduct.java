package com.example.webnovel.order.domain;

import com.example.webnovel.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Id
    @GeneratedValue
    @Column(name = "order_prouct_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer orderPrice;

    private Integer count;

    public OrderProduct(Product product, int orderPrice, int count) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderProduct createOrderProduct(Product product, int orderPrice, int count) {
        return new OrderProduct(product, orderPrice, count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
