package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class ProjectNameRequest {

    private final String projectID;
    private final String projectName;

    private ProjectNameRequest(ProjectNameRequestBuilder builder) {
        this.projectID = builder.projectID;
        this.projectName = builder.projectName;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public static ProjectNameRequestBuilder builder() {
        return new ProjectNameRequestBuilder();
    }

    public static class ProjectNameRequestBuilder implements Builder<ProjectNameRequest> {

        private String projectID;
        private String projectName;

        public ProjectNameRequestBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public ProjectNameRequestBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        @Override
        public ProjectNameRequest build() {
            return new ProjectNameRequest(this);
        }
    }
}
