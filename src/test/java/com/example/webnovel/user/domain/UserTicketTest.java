package com.example.webnovel.user.domain;

import com.example.webnovel.user.exception.InvalidCountException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTicketTest {

    @Test
    void create() {
        UserTicket userTicket = new UserTicket(1L, 5);

        assertThat(userTicket.getTicketCount()).isEqualTo(5);
    }

    @Test
    void createWithInvalidCount() {
        assertThrows(InvalidCountException.class, () -> new UserTicket(1L, -1));
    }

    @Test
    void changeTotalCount() {
        UserTicket userTicket = new UserTicket(1L, 5);
        UserTicket changedUserTicket = userTicket.changeTotalCount(1);

        assertThat(changedUserTicket.getTicketCount()).isEqualTo(6);
    }

    @Test
    void changeTotalCountWithInvalidCount() {
        UserTicket userTicket = new UserTicket(1L, 5);
        assertThrows(InvalidCountException.class, () -> userTicket.changeTotalCount(-6));
    }
}
