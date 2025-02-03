package io.litmuschaos.exception;

import java.io.IOException;

public class IOExceptionHolder extends RuntimeException {

    IOException exception;

    public IOExceptionHolder(IOException exception) {
        this.exception = exception;
    }

    public IOException getException() {
        return exception;
    }
}
