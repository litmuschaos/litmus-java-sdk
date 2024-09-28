package io.litmuschaos.request;

public class ProjectNameRequest {

    private String projectID;
    private String projectName;

    public ProjectNameRequest(String projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }
}