package io.litmuschaos.response;

public class ProjectMemberResponse {
    private String userID;
    private String username;
    private String email;
    private String name;
    private String role;
    private String invitation;
    private long joinedAt;
    public ProjectMemberResponse() {}
    public ProjectMemberResponse(String userID, String username, String email, String name, String role, String invitation, long joinedAt) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.name = name;
        this.role = role;
        this.invitation = invitation;
        this.joinedAt = joinedAt;
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
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getInvitation() {
        return invitation;
    }
    public long getJoinedAt() {
        return joinedAt;
    }
    @Override
    public String toString() {
        return "ProjectMember{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", invitation='" + invitation + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
