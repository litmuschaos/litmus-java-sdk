package io.litmuschaos.response;

import java.util.Objects;

public class UpdateCronExperimentStateResponse {
    private Boolean result;

    public UpdateCronExperimentStateResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "UpdateCronExperimentState{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCronExperimentStateResponse that = (UpdateCronExperimentStateResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
