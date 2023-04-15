package com.example.webnovel.user.domain;

import com.example.webnovel.global.model.BaseEntity;
import com.example.webnovel.user.exception.InvalidCountException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserTicket extends BaseEntity {

    @Getter
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Getter
    private int ticketCount;

    public UserTicket(Long userId, int ticketCount) {
        if (ticketCount < 0) {
            throw new InvalidCountException(ticketCount);
        }
        this.userId = userId;
        this.ticketCount = ticketCount;
    }

    public UserTicket changeTotalCount(int count) {
        int newCount = this.ticketCount + count;
        if (newCount < 0) {
            throw new InvalidCountException(ticketCount);
        }
        return new UserTicket(this.userId, newCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTicket)) return false;
        UserTicket that = (UserTicket) o;
        return Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
