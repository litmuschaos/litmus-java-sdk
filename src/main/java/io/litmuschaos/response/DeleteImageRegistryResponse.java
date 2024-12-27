package io.litmuschaos.response;

import java.util.Objects;

public class DeleteImageRegistryResponse {
    private String message;

    public DeleteImageRegistryResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DeleteImageRegistryResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteImageRegistryResponse that = (DeleteImageRegistryResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
