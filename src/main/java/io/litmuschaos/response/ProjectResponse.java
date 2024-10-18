package io.litmuschaos.response;

import java.util.List;

public class ProjectResponse {

    private Long updatedAt;
    private Long createdAt;
    private CreatedBy createdBy;
    private UpdatedBy updatedBy;
    private Boolean isRemoved;
    private String projectID;
    private String name;
    private List<ProjectMemberResponse> members;
    private String state;
    private List<String> tags;
    private String description;

    public ProjectResponse(Long updatedAt, Long createdAt, CreatedBy createdBy, UpdatedBy updatedBy,
            Boolean isRemoved, String projectID, String name, List<ProjectMemberResponse> members,
            String state, List<String> tags, String description) {
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isRemoved = isRemoved;
        this.projectID = projectID;
        this.name = name;
        this.members = members;
        this.state = state;
        this.tags = tags;
        this.description = description;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public UpdatedBy getUpdatedBy() {
        return updatedBy;
    }

    public Boolean isRemoved() {
        return isRemoved;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getName() {
        return name;
    }

    public List<ProjectMemberResponse> getMembers() {
        return members;
    }

    public String getState() {
        return state;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", isRemoved=" + isRemoved +
                ", projectID='" + projectID + '\'' +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", state='" + state + '\'' +
                ", tags=" + tags +
                ", description='" + description + '\'' +
                '}';
    }

    public static class CreatedBy {

        private String userID;
        private String username;
        private String email;

        public CreatedBy(String userID, String username, String email) {
            this.userID = userID;
            this.username = username;
            this.email = email;
        }

        public String getUserID() {
            return userID;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "CreatedBy{" +
                    "userID='" + userID + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static class UpdatedBy {

        private String userID;
        private String username;
        private String email;

        public UpdatedBy(String userID, String username, String email) {
            this.userID = userID;
            this.username = username;
            this.email = email;
        }

        public String getUserID() {
            return userID;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "UpdatedBy{" +
                    "userID='" + userID + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
