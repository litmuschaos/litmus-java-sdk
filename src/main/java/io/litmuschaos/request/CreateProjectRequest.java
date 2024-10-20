package io.litmuschaos.request;

import io.litmuschaos.util.Builder;
import java.util.List;

public class CreateProjectRequest {

    private final String projectName;
    private final String description;
    private final List<String> tags;

    private CreateProjectRequest(CreateProjectRequestBuilder builder) {
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.tags = builder.tags;
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

    public static CreateProjectRequestBuilder builder() {
        return new CreateProjectRequestBuilder();
    }

    public static class CreateProjectRequestBuilder implements Builder<CreateProjectRequest> {

        private String projectName;
        private String description;
        private List<String> tags;

        public CreateProjectRequestBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public CreateProjectRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateProjectRequestBuilder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        @Override
        public CreateProjectRequest build() {
            return new CreateProjectRequest(this);
        }
    }
}
