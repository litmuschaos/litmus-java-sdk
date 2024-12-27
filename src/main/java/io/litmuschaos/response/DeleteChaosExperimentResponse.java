package io.litmuschaos.response;

import java.util.Objects;

public class DeleteChaosExperimentResponse {
    private Boolean result;

    public DeleteChaosExperimentResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DeleteChaosExperimentResponse{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteChaosExperimentResponse that = (DeleteChaosExperimentResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
