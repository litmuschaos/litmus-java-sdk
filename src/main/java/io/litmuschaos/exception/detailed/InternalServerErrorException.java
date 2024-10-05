package io.litmuschaos.exception.detailed;

import io.litmuschaos.exception.LitmusApiException;

public class InternalServerErrorException extends LitmusApiException {
    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
