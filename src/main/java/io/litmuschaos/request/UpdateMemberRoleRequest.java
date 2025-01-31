package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class UpdateMemberRoleRequest {

    private final String projectID;
    private final String userID;
    private final String role;

    public UpdateMemberRoleRequest(String projectID, String userID, String role) {
        this.projectID = projectID;
        this.userID = userID;
        this.role = role;
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

    public static UpdateMemberRoleRequestBuilder builder() {
        return new UpdateMemberRoleRequestBuilder();
    }

    public static class UpdateMemberRoleRequestBuilder implements Builder<UpdateMemberRoleRequest> {

        private String projectID;
        private String userID;
        private String role;

        public UpdateMemberRoleRequestBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public UpdateMemberRoleRequestBuilder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public UpdateMemberRoleRequestBuilder role(String role) {
            this.role = role;
            return this;
        }

        @Override
        public UpdateMemberRoleRequest build() {
            return new UpdateMemberRoleRequest(projectID, userID, role);
        }
    }
}
