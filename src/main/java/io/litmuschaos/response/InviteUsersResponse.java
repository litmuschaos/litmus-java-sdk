package io.litmuschaos.response;

public class InviteUsersResponse {

    private long updatedAt;
    private long createdAt;
    private CreatedBy createdBy;
    private UpdatedBy updatedBy;
    private boolean isRemoved;
    private String userID;
    private String username;
    private String salt;
    private String email;
    private String name;
    private String role;
    private boolean isInitialLogin;

    public static class CreatedBy {
        private String userID;
        private String username;
        private String email;

        public CreatedBy(String userID, String username, String email) {
            this.userID = userID;
            this.username = username;
            this.email = email;
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
    }

    public InviteUsersResponse(long updatedAt, long createdAt, CreatedBy createdBy, UpdatedBy updatedBy, boolean isRemoved, String userID, String username, String salt, String email, String name, String role, boolean isInitialLogin) {
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

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public UpdatedBy getUpdatedBy() {
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
