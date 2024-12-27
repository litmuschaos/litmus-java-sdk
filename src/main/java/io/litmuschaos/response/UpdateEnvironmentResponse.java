package io.litmuschaos.response;

import java.util.Objects;

public class UpdateEnvironmentResponse {
    private String message;

    public UpdateEnvironmentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UpdateEnvironmentResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEnvironmentResponse that = (UpdateEnvironmentResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
