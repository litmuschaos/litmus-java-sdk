package io.litmuschaos.response;

public class SendInvitationResponse {

    private String userID;
    private String userName;
    private String name;
    private String role;
    private String email;
    private String invitation;
    private String joinedAt;
    private String deactivatedAt;

    public SendInvitationResponse(String userID, String userName, String name, String role, String email, String invitation, String joinedAt, String deactivatedAt) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.role = role;
        this.email = email;
        this.invitation = invitation;
        this.joinedAt = joinedAt;
        this.deactivatedAt = deactivatedAt;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getInvitation() {
        return invitation;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public String getDeactivatedAt() {
        return deactivatedAt;
    }
}
