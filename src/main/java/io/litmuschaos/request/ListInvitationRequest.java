package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class ListInvitationRequest {

    private final String projectId;
    private final String userId;

    private ListInvitationRequest(ListInvitationRequestBuilder builder) {
        this.projectId = builder.projectId;
        this.userId = builder.userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public static ListInvitationRequestBuilder builder() {
        return new ListInvitationRequestBuilder();
    }

    public static class ListInvitationRequestBuilder implements Builder<ListInvitationRequest> {
        private String projectId;
        private String userId;

        public ListInvitationRequestBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public ListInvitationRequestBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public ListInvitationRequest build() {
            return new ListInvitationRequest(this);
        }
    }
}
