package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class PasswordResetRequest {

    private final String username;
    private final String newPassword;

    private PasswordResetRequest(PasswordResetRequestBuilder builder) {
        this.username = builder.username;
        this.newPassword = builder.newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public static class PasswordResetRequestBuilder implements Builder<PasswordResetRequest> {
        private String username;
        private String newPassword;

        public PasswordResetRequestBuilder() {}

        public PasswordResetRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public PasswordResetRequestBuilder newPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public PasswordResetRequest build() {
            return new PasswordResetRequest(this);
        }
    }

    public static PasswordResetRequestBuilder builder() {
        return new PasswordResetRequestBuilder();
    }
}
