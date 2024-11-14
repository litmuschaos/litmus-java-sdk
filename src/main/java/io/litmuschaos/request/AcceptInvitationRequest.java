package io.litmuschaos.request;

public class AcceptInvitationRequest {

    private String projectId;
    private String userId;

    public AcceptInvitationRequest(String projectId, String userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;
        private String userId;

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public AcceptInvitationRequest build() {
            return new AcceptInvitationRequest(this.projectId, this.userId);
        }
    }
}
