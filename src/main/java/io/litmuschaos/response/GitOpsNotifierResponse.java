package io.litmuschaos.response;

import java.util.Objects;

public class GitOpsNotifierResponse {
    private String message;

    public GitOpsNotifierResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "GitOpsNotifierResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GitOpsNotifierResponse that = (GitOpsNotifierResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
