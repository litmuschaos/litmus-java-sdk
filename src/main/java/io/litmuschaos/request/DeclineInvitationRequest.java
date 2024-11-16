package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class DeclineInvitationRequest {

    private final String projectId;
    private final String userId;

    private DeclineInvitationRequest(DeclineInvitationRequestBuilder builder) {
        this.projectId = builder.projectId;
        this.userId = builder.userId;
    }

    public static DeclineInvitationRequestBuilder builder() {
        return new DeclineInvitationRequestBuilder();
    }

    public static class DeclineInvitationRequestBuilder implements Builder<DeclineInvitationRequest> {
        private String projectId;
        private String userId;

        public DeclineInvitationRequestBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public DeclineInvitationRequestBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public DeclineInvitationRequest build() {
            return new DeclineInvitationRequest(this);
        }
    }

}
