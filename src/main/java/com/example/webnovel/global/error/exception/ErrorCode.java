package com.example.webnovel.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "001", " Invalid Input"),
    METHOD_NOT_ALLOWED(405, "002", " Invalid Input "),
    ENTITY_NOT_FOUND(400, "003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "004", "Server Error"),
    INVALID_TYPE_VALUE(400, "005", " Invalid Type"),
    HANDLE_ACCESS_DENIED(403, "006", "Access is Denied"),
    EMAIL_DUPLICATION(400, "007", "Duplicated Mail"),
    INVALID_TICKET_COUNT(400, "008", "Invalid ticket Count");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
