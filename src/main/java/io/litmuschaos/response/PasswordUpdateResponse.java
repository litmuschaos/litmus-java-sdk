package io.litmuschaos.response;

public class PasswordUpdateResponse {

    private String message;

    private String projectID;

    public PasswordUpdateResponse(String message, String projectId) {
        this.message = message;
        this.projectID = projectId;
    }

    public String getMessage() {
        return message;
    }

    public String getProjectID() {
        return projectID;
    }
}
