package io.litmuschaos.response;

import java.util.Objects;

public class UpdateGitOpsResponse {
    private Boolean result;

    public UpdateGitOpsResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "UpdateGitOpsResponse{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGitOpsResponse that = (UpdateGitOpsResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}
