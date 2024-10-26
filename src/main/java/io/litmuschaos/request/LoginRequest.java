package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class LoginRequest {

    private final String username;
    private final String password;

    private LoginRequest(LoginRequestBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public static LoginRequestBuilder builder() {
        return new LoginRequestBuilder();
    }

    public static class LoginRequestBuilder implements Builder<LoginRequest> {

        private String username;
        private String password;

        public LoginRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LoginRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(this);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
