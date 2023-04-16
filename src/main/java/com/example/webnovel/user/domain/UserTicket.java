package com.example.webnovel.user.domain;

import com.example.webnovel.global.model.BaseEntity;
import com.example.webnovel.user.exception.InvalidCountException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserTicket extends BaseEntity {

    @Getter
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    private int ticketCount;

    public UserTicket(Long userId, int ticketCount) {
        if (ticketCount < 0) {
            throw new InvalidCountException(ticketCount);
        }
        this.userId = userId;
        this.ticketCount = ticketCount;
    }

    public void changeTotalCount(int count) {
        int newCount = this.ticketCount + count;
        if (newCount < 0) {
            throw new InvalidCountException(ticketCount);
        }

        this.ticketCount = newCount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
