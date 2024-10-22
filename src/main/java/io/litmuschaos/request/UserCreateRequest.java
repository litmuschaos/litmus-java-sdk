package io.litmuschaos.request;

public class UserCreateRequest {

    private final String username;
    private final String password;
    private final String role;
    private final String email;
    private final String name;

    private UserCreateRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
        this.email = builder.email;
        this.name = builder.name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private String username;
        private String password;
        private String role;
        private String email;
        private String name;

        public Builder() {}

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public UserCreateRequest build() {
            return new UserCreateRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}

