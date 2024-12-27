package io.litmuschaos.response;

import java.util.Objects;

public class EnableGitOpsResponse {
    private Boolean enabled;

    public EnableGitOpsResponse(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "EnableGitOpsResponse{" +
                "enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnableGitOpsResponse that = (EnableGitOpsResponse) o;
        return Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(enabled);
    }
}
