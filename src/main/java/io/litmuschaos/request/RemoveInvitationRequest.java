package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class RemoveInvitationRequest {

    private final String projectId;
    private final String userId;

    private RemoveInvitationRequest(RemoveInvitationRequestBuilder builder) {
        this.projectId = builder.projectId;
        this.userId = builder.userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public static RemoveInvitationRequestBuilder builder() {
        return new RemoveInvitationRequestBuilder();
    }

    public static class RemoveInvitationRequestBuilder implements Builder<RemoveInvitationRequest> {
        private String projectId;
        private String userId;

        public RemoveInvitationRequestBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public RemoveInvitationRequestBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public RemoveInvitationRequest build() {
            return new RemoveInvitationRequest(this);
        }
    }
}
