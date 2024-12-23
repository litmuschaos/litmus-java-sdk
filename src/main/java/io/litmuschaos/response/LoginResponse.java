package io.litmuschaos.response;

import io.litmuschaos.model.LitmusAuthToken;

public class LoginResponse {

    private LitmusAuthToken accessToken;
    private String expiresIn;
    private String projectID;
    private String projectRole;
    private String type;

    public LoginResponse(LitmusAuthToken accessToken, String expiresIn, String projectID, String projectRole, String type) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.projectID = projectID;
        this.projectRole = projectRole;
        this.type = type;
    }

    public LitmusAuthToken getAccessToken() {
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

    @Override
    public String toString() {
        return "LoginResponse{" +
                "accessToken='" + accessToken.getTokenValue() + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", projectID='" + projectID + '\'' +
                ", projectRole='" + projectRole + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
