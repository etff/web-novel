package com.example.webnovel.order.domain;

import com.example.webnovel.global.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long userId;

    private LocalDateTime orderDate;

    public static Order createOrder(Long userId, OrderProduct... orderProducts) {
        Order order = new Order(userId);

        for (OrderProduct orderItem : orderProducts) {
            order.addOrderProduct(orderItem);
        }
        return order;
    }

    private Order(Long userId) {
        this.userId = userId;
        this.orderStatus = OrderStatus.ORDERED;
        this.orderDate = LocalDateTime.now();
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderItems.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderProduct::getTotalPrice)
                .sum();
    }
}
