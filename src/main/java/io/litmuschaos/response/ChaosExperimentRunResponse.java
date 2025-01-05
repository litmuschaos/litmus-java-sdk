package io.litmuschaos.response;

import java.util.Objects;

public class ChaosExperimentRunResponse {
    private String message;

    public ChaosExperimentRunResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ChaosExperimentRunResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChaosExperimentRunResponse that = (ChaosExperimentRunResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
