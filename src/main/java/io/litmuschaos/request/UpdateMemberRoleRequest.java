package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class UpdateMemberRoleRequest {

    private final String projectID;
    private final String projectName;

    public UpdateMemberRoleRequest(String projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public static UpdateMemberRoleRequestBuilder builder() {
        return new UpdateMemberRoleRequestBuilder();
    }

    public static class UpdateMemberRoleRequestBuilder implements Builder<UpdateMemberRoleRequest> {

        private String projectID;
        private String projectName;

        public UpdateMemberRoleRequestBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public UpdateMemberRoleRequestBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        @Override
        public UpdateMemberRoleRequest build() {
            return new UpdateMemberRoleRequest(this.projectID, this.projectName);
        }
    }
}
