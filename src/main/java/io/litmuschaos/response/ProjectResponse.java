package io.litmuschaos.response;

import java.util.List;

public class ProjectResponse {

    private long updatedAt;
    private long createdAt;
    private CreatedBy createdBy;
    private UpdatedBy updatedBy;
    private boolean isRemoved;
    private String projectID;
    private String name;
    private List<ProjectMember> members;
    private String state;
    private List<String> tags;
    private String description;

    public ProjectResponse(long updatedAt, long createdAt, CreatedBy createdBy, UpdatedBy updatedBy,
            boolean isRemoved, String projectID, String name, List<ProjectMember> members,
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

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public UpdatedBy getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UpdatedBy updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectMember> getMembers() {
        return members;
    }

    public void setMembers(List<ProjectMember> members) {
        this.members = members;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectResponse{" + "updatedAt=" + updatedAt + ", createdAt=" + createdAt
                + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", isRemoved="
                + isRemoved + ", projectID='" + projectID + '\'' + ", name='" + name + '\''
                + ", members=" + members + ", state='" + state + '\'' + ", tags=" + tags
                + ", description='" + description + '\'' + '}';
    }

    public static class ProjectMember {

        private String userID;
        private String username;
        private String email;
        private String name;
        private String role;
        private String invitation;
        private long joinedAt;

        public ProjectMember(String userID, String username, String email, String name, String role,
                String invitation, long joinedAt) {
            this.userID = userID;
            this.username = username;
            this.email = email;
            this.name = name;
            this.role = role;
            this.invitation = invitation;
            this.joinedAt = joinedAt;
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

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }

        public String getInvitation() {
            return invitation;
        }

        public long getJoinedAt() {
            return joinedAt;
        }

        @Override
        public String toString() {
            return "ProjectMember{" + "userID='" + userID + '\'' + ", username='" + username + '\''
                    + ", email='" + email + '\'' + ", name='" + name + '\'' + ", role='" + role
                    + '\'' + ", invitation='" + invitation + '\'' + ", joinedAt=" + joinedAt + '}';
        }
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
            return "CreatedBy{" + "userID='" + userID + '\'' + ", username='" + username + '\''
                    + ", email='" + email + '\'' + '}';
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
            return "UpdatedBy{" + "userID='" + userID + '\'' + ", username='" + username + '\''
                    + ", email='" + email + '\'' + '}';
        }
    }
}
