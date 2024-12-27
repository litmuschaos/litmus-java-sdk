package io.litmuschaos.response;

import java.util.Objects;

public class StopExperimentRunsResponse {
    private Boolean result;

    public StopExperimentRunsResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "StopExperimentRunsResponse{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StopExperimentRunsResponse that = (StopExperimentRunsResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
