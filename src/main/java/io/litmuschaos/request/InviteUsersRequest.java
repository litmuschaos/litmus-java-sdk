package io.litmuschaos.request;

public class InviteUsersRequest {

    private String projectId;
    private String userId;

    public InviteUsersRequest(String projectId, String userId) {
        this.projectId = projectId;
        this.userId = userId;
    }
}
