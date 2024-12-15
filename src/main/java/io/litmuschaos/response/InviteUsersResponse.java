package io.litmuschaos.response;

public class InviteUsersResponse {

    private long updatedAt;
    private long createdAt;
    private UserInfo createdBy;
    private UserInfo updatedBy;
    private boolean isRemoved;
    private String userID;
    private String username;
    private String salt;
    private String email;
    private String name;
    private String role;
    private boolean isInitialLogin;

    public class UserInfo {
        private String userID;
        private String username;
        private String email;

        public UserInfo(String userID, String username, String email) {
            this.userID = userID;
            this.username = username;
            this.email = email;
        }
    }

    public InviteUsersResponse(long updatedAt, long createdAt, UserInfo createdBy, UserInfo updatedBy, boolean isRemoved, String userID, String username, String salt, String email, String name, String role, boolean isInitialLogin) {
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isRemoved = isRemoved;
        this.userID = userID;
        this.username = username;
        this.salt = salt;
        this.email = email;
        this.name = name;
        this.role = role;
        this.isInitialLogin = isInitialLogin;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public UserInfo getCreatedBy() {
        return createdBy;
    }

    public UserInfo getUpdatedBy() {
        return updatedBy;
    }

    public boolean getIsRemoved() {
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

    public boolean getIsInitialLogin() {
        return isInitialLogin;
    }
}
