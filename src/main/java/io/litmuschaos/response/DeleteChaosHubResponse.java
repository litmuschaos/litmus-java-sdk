package io.litmuschaos.response;

import java.util.Objects;

public class DeleteChaosHubResponse {
    private Boolean result;

    public DeleteChaosHubResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DeleteChaosHubResponse{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteChaosHubResponse that = (DeleteChaosHubResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
