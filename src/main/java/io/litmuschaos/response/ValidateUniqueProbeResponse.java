package io.litmuschaos.response;

import java.util.Objects;

public class ValidateUniqueProbeResponse {
    private Boolean result;

    public ValidateUniqueProbeResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ValidateUniqueProbeResponse{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ValidateUniqueProbeResponse that = (ValidateUniqueProbeResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
