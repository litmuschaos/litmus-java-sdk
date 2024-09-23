package io.litmuschaos.request;

public class LeaveProjectRequest {
    String projectID;
    String userID;
    public LeaveProjectRequest(String projectID, String userID) {
        this.projectID = projectID;
        this.userID = userID;
    }
}