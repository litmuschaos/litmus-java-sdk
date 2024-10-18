package io.litmuschaos.request;

public class LeaveProjectRequest {

    private final String projectID;
    private final String userID;

    private LeaveProjectRequest(Builder builder) {
        this.projectID = builder.projectID;
        this.userID = builder.userID;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectID;
        private String userID;

        public Builder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public Builder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public LeaveProjectRequest build() {
            return new LeaveProjectRequest(this);
        }
    }

    public String getProjectID() {
        return projectID;
    }

    public String getUserID() {
        return userID;
    }

}
