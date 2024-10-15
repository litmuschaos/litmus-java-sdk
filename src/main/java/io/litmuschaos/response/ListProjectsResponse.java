package io.litmuschaos.response;

import java.util.List;

public class ListProjectsResponse {

    private List<ProjectResponse> projects;
    private Integer totalNumberOfProjects;

    public ListProjectsResponse(List<ProjectResponse> projects, Integer totalNumberOfProjects) {
        this.projects = projects;
        this.totalNumberOfProjects = totalNumberOfProjects;
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public Integer getTotalNumberOfProjects() {
        return totalNumberOfProjects;
    }

    @Override
    public String toString() {
        return "ListProjectsResponse{" +
                "projects=" + projects +
                ", totalNumberOfProjects=" + totalNumberOfProjects +
                '}';
    }
}
