package io.litmuschaos.exception.detailed;

import io.litmuschaos.exception.LitmusApiException;

public class UnauthorizedException extends LitmusApiException {
    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
