package io.litmuschaos.response;

public class ListInvitationResponse {

    private String projectID;
    private String projectName;
    private ProjectUser projectOwner;
    private String invitationRole;

    public ListInvitationResponse(String projectID, String projectName, ProjectUser projectOwner, String invitationRole) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.invitationRole = invitationRole;
    }

    public static class ProjectUser {
        private String userID;
        private String username;
        private String email;
        private String name;
        private String role;
        private String invitation;
        private long joinedAt;

        public  ProjectUser(String userID, String username, String email, String name, String role, String invitation, long joinedAt) {
            this.userID = userID;
            this.username = username;
            this.email = email;
            this.name = name;
            this.role = role;
            this.invitation = invitation;
            this.joinedAt = joinedAt;
        }

    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectUser getProjectOwner() {
        return projectOwner;
    }

    public String getInvitationRole() {
        return invitationRole;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectOwner(ProjectUser projectOwner) {
        this.projectOwner = projectOwner;
    }

    public void setInvitationRole(String invitationRole) {
        this.invitationRole = invitationRole;
    }

}
