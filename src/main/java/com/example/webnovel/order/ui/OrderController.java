package com.example.webnovel.order.ui;

import com.example.webnovel.order.application.OrderService;
import com.example.webnovel.order.dto.OrderCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity createOrder(@Valid @RequestBody OrderCreateRequest request) {
        final Long orderId = orderService.createOrder(request.getProductId(), request.getUserId(), request.getToken());
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @PostMapping("/token")
    public ResponseEntity createToken() {
        final String token = orderService.createToken();
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
