package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class SendInvitationRequest {

    private final String projectID;
    private final String userID;
    private final String role;

    private SendInvitationRequest(SendInvitationRequestBuilder builder) {
        this.projectID = builder.projectID;
        this.userID = builder.userID;
        this.role = builder.role;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getUserID() {
        return userID;
    }

    public String getRole() {
        return role;
    }

    public static SendInvitationRequestBuilder builder() {
        return new SendInvitationRequestBuilder();
    }

    public static class SendInvitationRequestBuilder implements Builder<SendInvitationRequest> {
        private String projectID;
        private String userID;
        private String role;

        public SendInvitationRequestBuilder projectId(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public SendInvitationRequestBuilder userId(String userID) {
            this.userID = userID;
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
