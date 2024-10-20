package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class LeaveProjectRequest {

    private final String projectID;
    private final String userID;

    private LeaveProjectRequest(LeaveProjectRequestBuilder builder) {
        this.projectID = builder.projectID;
        this.userID = builder.userID;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getUserID() {
        return userID;
    }

    public static LeaveProjectRequestBuilder builder() {
        return new LeaveProjectRequestBuilder();
    }

    public static class LeaveProjectRequestBuilder implements Builder<LeaveProjectRequest> {

        private String projectID;
        private String userID;

        public LeaveProjectRequestBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public LeaveProjectRequestBuilder userID(String userID) {
            this.userID = userID;
            return this;
        }

        @Override
        public LeaveProjectRequest build() {
            return new LeaveProjectRequest(this);
        }
    }
}
