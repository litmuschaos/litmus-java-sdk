package io.litmuschaos.request;

import java.util.List;

public class CreateProjectRequest {

    private final String projectName;
    private final String description;
    private final List<String> tags;

    private CreateProjectRequest(Builder builder) {
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.tags = builder.tags;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String projectName;
        private String description;
        private List<String> tags;

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public CreateProjectRequest build() {
            return new CreateProjectRequest(this);
        }
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }
}
