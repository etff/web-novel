package com.example.webnovel.order.application;

import com.example.webnovel.global.error.exception.EntityNotFoundException;
import com.example.webnovel.order.domain.Order;
import com.example.webnovel.order.domain.OrderProduct;
import com.example.webnovel.order.domain.TicketOrderEvent;
import com.example.webnovel.order.domain.TrxToken;
import com.example.webnovel.order.domain.type.TokenStatus;
import com.example.webnovel.order.infra.OrderRepository;
import com.example.webnovel.order.infra.TrxTokenRepository;
import com.example.webnovel.product.domain.Product;
import com.example.webnovel.product.domain.Ticket;
import com.example.webnovel.product.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final TrxTokenRepository trxTokenRepository;

    private final ProductRepository productRepository;

    private final ApplicationEventPublisher eventPublisher;

    public Long createOrder(Long productId, Long userId, String token) {
        TrxToken trxToken = trxTokenRepository.findByIdAndTokenStatus(token, TokenStatus.PUBLISHED)
                .orElseThrow(() -> new EntityNotFoundException("Token is not valid"));
        trxToken.changeStatusUsed();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product is not valid"));
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, product.getPrice(), 1);

        Order order = Order.createOrder(userId, orderProduct);
        Order saved = orderRepository.save(order);

        if (product instanceof Ticket) {
            Ticket ticket = (Ticket) product;
            eventPublisher.publishEvent(new TicketOrderEvent(userId, ticket.getQuantity()));
        }

        return saved.getId();
    }

    public String createToken() {
        String token = UUID.randomUUID().toString();
        TrxToken trxToken = new TrxToken(token);
        trxTokenRepository.save(trxToken);
        return token;
    }
}
