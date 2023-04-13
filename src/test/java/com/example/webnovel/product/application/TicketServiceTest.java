package com.example.webnovel.product.application;

import com.example.webnovel.product.domain.Ticket;
import com.example.webnovel.product.domain.type.ProductStatus;
import com.example.webnovel.product.exception.ProductNotFoundException;
import com.example.webnovel.product.infra.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    void create_ticket() {
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

    @DisplayName("티켓 상태를 변경할 수 있다")
    @Test
    void change_ticket_status() {
        // given
        given(productRepository.findById(any(Long.class)))
                .willReturn(Optional.of(new Ticket(1L, "티켓", 1000, 10)));

        // when

        assertDoesNotThrow(
                () -> ticketService.changeProductStatus(1L, ProductStatus.SELLING)
        );
    }

    @DisplayName("티켓 상태를 변경할 때 티켓이 존재하지 않으면 예외를 던진다")
    @Test
    void change_status_with_not_found_ticket() {
        // given
        given(productRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // when
        assertThatCode(() -> ticketService.changeProductStatus(1L, ProductStatus.SELLING))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
