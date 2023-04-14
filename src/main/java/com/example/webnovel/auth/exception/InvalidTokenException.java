package com.example.webnovel.auth.exception;

import com.example.webnovel.global.error.exception.ErrorCode;
import com.example.webnovel.global.error.exception.InvalidValueException;

public class InvalidTokenException extends InvalidValueException {

    public InvalidTokenException(String value) {
        super(value, ErrorCode.HANDLE_ACCESS_DENIED);
    }
}
