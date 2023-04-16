package com.example.webnovel.order.infra;

import com.example.webnovel.order.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
