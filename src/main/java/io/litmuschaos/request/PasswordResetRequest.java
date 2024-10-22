package io.litmuschaos.request;

public class PasswordResetRequest {

    private final String username;
    private final String newPassword;

    private PasswordResetRequest(Builder builder) {
        this.username = builder.username;
        this.newPassword = builder.newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public static class Builder {
        private String username;
        private String newPassword;

        public Builder() {}

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder newPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public PasswordResetRequest build() {
            return new PasswordResetRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
