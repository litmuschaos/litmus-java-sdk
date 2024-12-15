package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class InviteUsersRequest {

    private final String projectId;
    private final String userId;

    private InviteUsersRequest(InviteUsersRequestBuilder builder) {
        this.projectId = builder.projectId;
        this.userId = builder.userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public static InviteUsersRequestBuilder builder() {
        return new InviteUsersRequestBuilder();
    }

    public static class InviteUsersRequestBuilder implements Builder<InviteUsersRequest> {

        private String projectId;
        private String userId;

        public InviteUsersRequestBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public InviteUsersRequestBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public InviteUsersRequest build() {
            return new InviteUsersRequest(this);
        }
    }
}
