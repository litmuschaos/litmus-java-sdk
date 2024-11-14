package io.litmuschaos.request;

public class DeclineInvitationRequest {

    private String projectId;
    private String userId;

    public DeclineInvitationRequest(String projectId, String userId) {
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

        public DeclineInvitationRequest build() {
            return new DeclineInvitationRequest(this.projectId, this.userId);
        }
    }

}
