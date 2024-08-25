package io.litmuschaos.auth.response;

public class LoginResponse {

    private String accessToken;
    private String expiresIn;
    private String projectID;
    private String projectRole;
    private String type;

    public LoginResponse(String accessToken, String expiresIn, String projectID, String projectRole, String type) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.projectID = projectID;
        this.projectRole = projectRole;
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public String getType() {
        return type;
    }
}
