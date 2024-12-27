package io.litmuschaos.response;

import java.util.Objects;

public class DisableGitOpsResponse {
    private Boolean result;

    public DisableGitOpsResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DisableGitOpsResponse{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DisableGitOpsResponse that = (DisableGitOpsResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
