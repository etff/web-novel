package com.example.webnovel.user.exception;

import com.example.webnovel.global.error.exception.ErrorCode;
import com.example.webnovel.global.error.exception.InvalidValueException;

public class EmailDuplicatedException extends InvalidValueException {

    public EmailDuplicatedException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}
