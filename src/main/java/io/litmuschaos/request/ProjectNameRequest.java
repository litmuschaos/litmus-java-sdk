package io.litmuschaos.request;

public class ProjectNameRequest {

    private final String projectID;
    private final String projectName;

    private ProjectNameRequest(Builder builder) {
        this.projectID = builder.projectID;
        this.projectName = builder.projectName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectID;
        private String projectName;

        public Builder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public ProjectNameRequest build() {
            return new ProjectNameRequest(this);
        }
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }
}
