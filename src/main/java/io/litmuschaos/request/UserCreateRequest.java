package io.litmuschaos.request;

import io.litmuschaos.enums.Role;
import io.litmuschaos.util.Builder;

public class UserCreateRequest {

    private final String username;
    private final String password;
    private final Role role;
    private final String email;
    private final String name;

    private UserCreateRequest(UserCreateRequestBuilder builder) {
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

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static UserCreateRequestBuilder builder(){
        return new UserCreateRequestBuilder();
    }

    public static class UserCreateRequestBuilder implements Builder<UserCreateRequest> {
        private String username;
        private String password;
        private Role role;
        private String email;
        private String name;

        public UserCreateRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserCreateRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserCreateRequestBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserCreateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserCreateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserCreateRequest build() {
            return new UserCreateRequest(this);
        }
    }
}

