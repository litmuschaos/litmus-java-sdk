package io.litmuschaos.request;

public class SendInvitationRequest {

    private String projectId;
    private String userId;
    private String role;

    public SendInvitationRequest(String projectId, String userId, String role) {
        this.projectId = projectId;
        this.userId = userId;
        this.role = role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;
        private String userId;
        private String role;

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public SendInvitationRequest build() {
            return new SendInvitationRequest(this.projectId, this.userId, this.role);
        }
    }

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
}
