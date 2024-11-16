package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class SendInvitationRequest {

    private final String projectId;
    private final String userId;
    private final String role;

    private SendInvitationRequest(SendInvitationRequestBuilder builder) {
        this.projectId = builder.projectId;
        this.userId = builder.userId;
        this.role = builder.role;
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

    public static SendInvitationRequestBuilder builder() {
        return new SendInvitationRequestBuilder();
    }

    public static class SendInvitationRequestBuilder implements Builder<SendInvitationRequest> {
        private String projectId;
        private String userId;
        private String role;

        public SendInvitationRequestBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public SendInvitationRequestBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public SendInvitationRequestBuilder role(String role) {
            this.role = role;
            return this;
        }

        @Override
        public SendInvitationRequest build() {
            return new SendInvitationRequest(this);
        }
    }
}
