package io.litmuschaos.request;

public class UserDetailsUpdateRequest {

    private final String name;
    private final String email;

    private UserDetailsUpdateRequest(UserDetailsUpdateRequestBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getUsername() {
        return name;
    }

    public String getNewPassword() {
        return email;
    }

    public static class UserDetailsUpdateRequestBuilder {
        private String name;
        private String email;

        public UserDetailsUpdateRequestBuilder() {}

        public UserDetailsUpdateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDetailsUpdateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDetailsUpdateRequest build() {
            return new UserDetailsUpdateRequest(this);
        }
    }

    public static UserDetailsUpdateRequestBuilder builder() {
        return new UserDetailsUpdateRequestBuilder();
    }
}
