package io.litmuschaos.response;

import java.util.Objects;

public class SyncChaosHubResponse {
    private String message;

    public SyncChaosHubResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SyncChaosHubResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SyncChaosHubResponse that = (SyncChaosHubResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
