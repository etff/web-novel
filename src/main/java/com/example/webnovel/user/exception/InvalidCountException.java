package com.example.webnovel.user.exception;

import com.example.webnovel.global.error.exception.ErrorCode;
import com.example.webnovel.global.error.exception.InvalidValueException;

public class InvalidCountException extends InvalidValueException {

    public InvalidCountException(final int count) {
        super("잘못된 개수: " + count, ErrorCode.INVALID_TICKET_COUNT);
    }
}
