package io.litmuschaos.exception.detailed;

import io.litmuschaos.exception.LitmusApiException;

public class ForbiddenException extends LitmusApiException {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

}
