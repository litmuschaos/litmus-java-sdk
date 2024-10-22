package io.litmuschaos.request;

public class UserStateUpdateRequest {

    private final String username;
    private final boolean isDeactivate;

    private UserStateUpdateRequest(Builder builder) {
        this.username = builder.username;
        this.isDeactivate = builder.isDeactivate;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDeactivate() {
        return isDeactivate;
    }

    public static class Builder {
        private String username;
        private boolean isDeactivate;

        public Builder() {}

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder isDeactivate(boolean isDeactivate) {
            this.isDeactivate = isDeactivate;
            return this;
        }

        public UserStateUpdateRequest build() {
            return new UserStateUpdateRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
