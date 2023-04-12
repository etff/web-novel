package com.example.webnovel.product.application;

import com.example.webnovel.product.domain.Ticket;
import com.example.webnovel.product.infra.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private ProductRepository productRepository;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketService(productRepository);
    }

    @DisplayName("티켓 생성하고 생성된 티켓의 아이디를 반환한다")
    @Test
    void createProduct() {
        // given
        final String name = "티켓";
        final Integer price = 1000;
        final Integer quantity = 10;
        final Ticket givenTicket = new Ticket(1L, name, price, quantity);
        given(productRepository.save(any(Ticket.class))).willReturn(givenTicket);

        // when
        final Long productId = ticketService.createProduct(name, price, quantity);

        // then
        assertNotNull(productId);
    }
}
