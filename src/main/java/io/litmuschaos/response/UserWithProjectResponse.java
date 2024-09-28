package io.litmuschaos.response;

import io.litmuschaos.response.ProjectResponse.CreatedBy;
import io.litmuschaos.response.ProjectResponse.UpdatedBy;
import java.util.List;

public class UserWithProjectResponse {

    private long updatedAt;
    private long createdAt;
    private CreatedBy createdBy;
    private UpdatedBy updatedBy;
    private boolean isRemoved;
    private String id;
    private String username;
    private String email;
    private String name;
    private List<ProjectResponse> projects;

    public UserWithProjectResponse(long updatedAt, long createdAt, CreatedBy createdBy,
            UpdatedBy updatedBy, boolean isRemoved, String id, String username, String email,
            String name, List<ProjectResponse> projects) {
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isRemoved = isRemoved;
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.projects = projects;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public UpdatedBy getUpdatedBy() {
        return updatedBy;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public String getId() {
        return id;
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

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", isRemoved=" + isRemoved +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", projects=" + projects +
                '}';
    }
}