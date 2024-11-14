package io.litmuschaos.request;

public class RemoveInvitationRequest {

    private String projectId;
    private String userId;

    public RemoveInvitationRequest(String projectId, String userId) {
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

        public RemoveInvitationRequest build() {
            return new RemoveInvitationRequest(this.projectId, this.userId);
        }
    }
}
