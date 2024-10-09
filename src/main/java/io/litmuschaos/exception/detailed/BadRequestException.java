package io.litmuschaos.exception.detailed;

import io.litmuschaos.exception.LitmusApiException;

public class BadRequestException extends LitmusApiException {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
