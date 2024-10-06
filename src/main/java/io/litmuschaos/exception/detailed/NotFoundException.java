package io.litmuschaos.exception.detailed;

import io.litmuschaos.exception.LitmusApiException;

public class NotFoundException extends LitmusApiException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
