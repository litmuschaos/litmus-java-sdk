package io.litmuschaos.request;

public class UserDetailsUpdateRequest {

    private final String id;
    private final String name;
    private final String email;

    private UserDetailsUpdateRequest(UserDetailsUpdateRequest.Builder builder) {
        this.id = builder.userID;
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getUsername() {
        return name;
    }

    public String getNewPassword() {
        return email;
    }

    public String getUserID(){
        return id;
    }

    public static class Builder {
        private String userID;
        private String name;
        private String email;

        public Builder() {}

        public UserDetailsUpdateRequest.Builder name(String name) {
            this.name = name;
            return this;
        }

        public UserDetailsUpdateRequest.Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserDetailsUpdateRequest.Builder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public UserDetailsUpdateRequest build() {
            return new UserDetailsUpdateRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
