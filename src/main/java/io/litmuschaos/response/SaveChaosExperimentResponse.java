package io.litmuschaos.response;

import java.util.Objects;

public class SaveChaosExperimentResponse {
    private String message;

    public SaveChaosExperimentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SaveChaosExperimentResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaveChaosExperimentResponse that = (SaveChaosExperimentResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
