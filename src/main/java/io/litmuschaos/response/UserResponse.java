package io.litmuschaos.response;

public class UserResponse {

    private final Long updatedAt;
    private final Long createdAt;
    private final CreatedBy createdBy;
    private final UpdatedBy updatedBy;
    private final Boolean isRemoved;
    private final String userID;
    private final String username;
    private final String salt;
    private final String email;
    private final String name;
    private final String role;
    private final boolean isInitialLogin;

    private UserResponse(Builder builder) {
        this.updatedAt = builder.updatedAt;
        this.createdAt = builder.createdAt;
        this.createdBy = builder.createdBy;
        this.updatedBy = builder.updatedBy;
        this.isRemoved = builder.isRemoved;
        this.userID = builder.userID;
        this.username = builder.username;
        this.salt = builder.salt;
        this.email = builder.email;
        this.name = builder.name;
        this.role = builder.role;
        this.isInitialLogin = builder.isInitialLogin;
    }

    public static class Builder {
        private Long updatedAt;
        private Long createdAt;
        private CreatedBy createdBy;
        private UpdatedBy updatedBy;
        private Boolean isRemoved;
        private String userID;
        private String username;
        private String salt;
        private String email;
        private String name;
        private String role;
        private boolean isInitialLogin;

        public Builder updatedAt(Long updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder createdAt(Long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder createdBy(CreatedBy createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder updatedBy(UpdatedBy updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder isRemoved(Boolean isRemoved) {
            this.isRemoved = isRemoved;
            return this;
        }

        public Builder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
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

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder isInitialLogin(boolean isInitialLogin) {
            this.isInitialLogin = isInitialLogin;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this);
        }
    }


    public static class CreatedBy {

        private String userID;
        private String username;
        private String email;

        public CreatedBy(String userID, String username, String email) {
            this.userID = userID;
            this.username = username;
            this.email = email;
        }

        public String getUserID() {
            return userID;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "CreatedBy{" +
                    "userID='" + userID + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static class UpdatedBy {

        private String userID;
        private String username;
        private String email;

        public UpdatedBy(String userID, String username, String email) {
            this.userID = userID;
            this.username = username;
            this.email = email;
        }

        public String getUserID() {
            return userID;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "UpdatedBy{" +
                    "userID='" + userID + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public UpdatedBy getUpdatedBy() {
        return updatedBy;
    }

    public Boolean isRemoved() {
        return isRemoved;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean isInitialLogin() {
        return isInitialLogin;
    }
}