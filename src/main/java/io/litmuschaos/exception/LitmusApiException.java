package io.litmuschaos.exception;

public class LitmusApiException extends Exception {

    public LitmusApiException(){
        super();
    }

    public LitmusApiException(String message) {
        super(message);
    }

    public LitmusApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
