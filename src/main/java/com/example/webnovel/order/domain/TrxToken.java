package com.example.webnovel.order.domain;

import com.example.webnovel.order.domain.type.TokenStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TrxToken {

    @Id
    @Column(name = "trx_key")
    private String id;

    @Enumerated(EnumType.STRING)
    private TokenStatus tokenStatus;

    @Version
    private Long version;

    public TrxToken(String uuid) {
        this.id = uuid;
        this.tokenStatus = TokenStatus.PUBLISHED;
    }

    public void changeStatusUsed() {
        this.tokenStatus = TokenStatus.USED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrxToken)) return false;
        TrxToken trxToken = (TrxToken) o;
        return Objects.equals(getId(), trxToken.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
