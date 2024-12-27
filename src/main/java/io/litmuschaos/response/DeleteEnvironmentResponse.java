package io.litmuschaos.response;

import java.util.Objects;

public class DeleteEnvironmentResponse {
    private String message;

    public DeleteEnvironmentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DeleteEnvironmentResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteEnvironmentResponse that = (DeleteEnvironmentResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
