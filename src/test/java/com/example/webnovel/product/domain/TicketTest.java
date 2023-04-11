package com.example.webnovel.product.domain;

import com.example.webnovel.product.domain.type.ProductStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

class TicketTest {

    @DisplayName("티켓 생성을 하고 생성할때 입력받은 값을 검증")
    @Test
    void create() {
        final Ticket ticket = new Ticket("test", 1000, 1);

        assertAll(
                () -> assertThat(ticket.getName()).isEqualTo("test"),
                () -> assertThat(ticket.getQuantity()).isEqualTo(1),
                () -> assertThat(ticket.getPrice()).isEqualTo(1000),
                () -> assertThat(ticket.getProductStatus()).isEqualTo(ProductStatus.REGISTERED)
        );
    }

    @DisplayName("티켓 생성시 가격이 0보다 작으면 예외를 던진다")
    @Test
    void create_with_negative_price() {
        assertThatCode(() -> {
            new Ticket("test", -1000, 1);
        });
    }

    @DisplayName("티켓 생성시 수량이 0보다 작으면 예외를 던진다")
    @Test
    void create_with_negative_quantity() {
        assertThatCode(() -> {
            new Ticket("test", 1000, -1);
        });
    }

    @DisplayName("티켓 생성시 이름이 null이면 예외를 던진다")
    @Test
    void create_with_null_name() {
        assertThatCode(() -> {
            new Ticket(null, 1000, 1);
        });
    }

    @Test
    void change_status() {
        // given
        final Ticket ticket = new Ticket("test", 1000, 1);
        // when
        ticket.changeStatus(ProductStatus.SOLD_OUT);
        // then
        assertThat(ticket.getProductStatus()).isEqualTo(ProductStatus.SOLD_OUT);
    }
}
