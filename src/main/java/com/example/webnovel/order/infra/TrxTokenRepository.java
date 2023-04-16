package com.example.webnovel.order.infra;

import com.example.webnovel.order.domain.TrxToken;
import com.example.webnovel.order.domain.type.TokenStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TrxTokenRepository extends CrudRepository<TrxToken, Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    Optional<TrxToken> findByIdAndTokenStatus(String token, TokenStatus tokenStatus);
}
