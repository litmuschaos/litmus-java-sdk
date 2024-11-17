package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class AcceptInvitationRequest {

    private final String projectId;
    private final String userId;

    private AcceptInvitationRequest(AcceptInvitationRequestBuilder builder) {
        this.projectId = builder.projectId;
        this.userId = builder.userId;
    }

    public static AcceptInvitationRequestBuilder builder() {
        return new AcceptInvitationRequestBuilder();
    }

    public static class AcceptInvitationRequestBuilder implements Builder<AcceptInvitationRequest> {
        private String projectId;
        private String userId;

        public AcceptInvitationRequestBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public AcceptInvitationRequestBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public AcceptInvitationRequest build() {
            return new AcceptInvitationRequest(this);
        }
    }
}
