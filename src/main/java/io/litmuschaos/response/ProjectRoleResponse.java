package io.litmuschaos.response;

public class ProjectRoleResponse {

    String role;

    public ProjectRoleResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "ProjectRoleResponse{" +
                "role='" + role + '\'' +
                '}';
    }
}
