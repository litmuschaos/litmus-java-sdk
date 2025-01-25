import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Example {

    private static final String HOST_URL = "http://127.0.0.1:3000";
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private final MockLitmusClient litmusClient = new MockLitmusClient(HOST_URL,TEST_TOKEN);

    @Test
    public void getTokens() throws IOException, LitmusApiException {
        String userId = "5ee70855-c77e-4a8f-a0d7-715bb5846bdd";
        ListTokensResponse response = litmusClient.getTokens(userId);
        assertThat(response).isInstanceOf(ListTokensResponse.class);
    }

    @Test
    public void createToken() throws IOException, LitmusApiException {
        TokenCreateRequest request = TokenCreateRequest.builder()
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .name("token name")
                .daysUntilExpiration(36500)
                .build();
        TokenCreateResponse response = litmusClient.createToken(request);
        assertThat(response).isInstanceOf(TokenCreateResponse.class);
    }

    // It is not working in local environment
    @Test
    public void deleteToken() throws IOException, LitmusApiException {
        TokenDeleteRequest request = TokenDeleteRequest.builder()
                .token(TEST_TOKEN)
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();
        CommonResponse response = litmusClient.deleteToken(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void getUser() throws IOException, LitmusApiException {
        String userId = "5ee70855-c77e-4a8f-a0d7-715bb5846bdd";
        UserResponse response = litmusClient.getUser(userId);
        assertThat(response).isInstanceOf(UserResponse.class);
    }

    @Test
    public void getUsers() throws IOException, LitmusApiException {
        List<UserResponse> response = litmusClient.getUsers();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void updatePassword() throws IOException, LitmusApiException {
        PasswordUpdateRequest request = PasswordUpdateRequest.builder().
                username("test_username").
                oldPassword("old password").
                newPassword("new password").
                build();
        PasswordUpdateResponse response = litmusClient.updatePassword(request);
        assertThat(response).isInstanceOf(PasswordUpdateResponse.class);
    }

    @Test
    public void createUser() throws IOException, LitmusApiException {
        UserCreateRequest request = UserCreateRequest.builder()
                .username("test_username")
                .password("test password")
                .role("admin") // Role can be admin, user
                .email("test@litmus.com")
                .build();
        UserResponse response = litmusClient.createUser(request);
        assertThat(response).isInstanceOf(UserResponse.class);

    }

    // only admin can call reset password
    @Test
    public void resetPassword() throws IOException, LitmusApiException {
        PasswordResetRequest request = PasswordResetRequest.builder()
                .username("test_username")
                .newPassword("new password")
                .build();
        CommonResponse response = litmusClient.resetPassword(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void updateUserDetails() throws IOException, LitmusApiException {
        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .name("updated name")
                .email("updated@litmus.com")
                .build();
        CommonResponse response = litmusClient.updateUserDetails(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void updateUserState() throws IOException, LitmusApiException {
        UserStateUpdateRequest request = UserStateUpdateRequest.builder()
                .username("test_username")
                .isDeactivate(true)
                .build();
        CommonResponse response = litmusClient.updateUserState(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void capabilities() throws IOException, LitmusApiException {
        CapabilityResponse response = litmusClient.capabilities();
        assertThat(response).isInstanceOf(CapabilityResponse.class);
    }

    @Test
    public void listProjects() throws IOException, LitmusApiException {
        ListProjectRequest request = ListProjectRequest.builder()
                .page(0)
                .limit(10)
                .sortField("name") // SortField can be name, time
                .createdByMe(true)
                .build();
        ListProjectsResponse response = litmusClient.listProjects(request);
        assertThat(response).isInstanceOf(ListProjectsResponse.class);
    }

    @Test
    public void createProject() throws IOException, LitmusApiException {
        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectName("test projectName")
                .description("test description")
                .tags(List.of("tag1", "tag2"))
                .build();
        ProjectResponse response = litmusClient.createProject(request);
        assertThat(response).isInstanceOf(ProjectResponse.class);
    }

    @Test
    public void updateProjectName() throws IOException, LitmusApiException {
        ProjectNameRequest request = ProjectNameRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .projectName("test projectName")
                .build();
        CommonResponse response = litmusClient.updateProjectName(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void getProject() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        ProjectResponse response = litmusClient.getProject(projectID);
        assertThat(response).isInstanceOf(ProjectResponse.class);
    }

    @Test
    public void getOwnerProjects() throws IOException, LitmusApiException {
        List<ProjectResponse> response = litmusClient.getOwnerProjects();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void leaveProject() throws IOException, LitmusApiException {
        LeaveProjectRequest request = LeaveProjectRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();
        CommonResponse response = litmusClient.leaveProject(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void getProjectRole() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        ProjectRoleResponse response = litmusClient.getProjectRole(projectID);
        assertThat(response).isInstanceOf(ProjectRoleResponse.class);
    }

    @Test
    public void getUserWithProject() throws IOException, LitmusApiException {
        String username = "test_username";
        UserWithProjectResponse response = litmusClient.getUserWithProject(username);
        assertThat(response).isInstanceOf(UserWithProjectResponse.class);
    }

    @Test
    public void getProjectsStats() throws IOException, LitmusApiException {
        List<ProjectsStatsResponse> response = litmusClient.getProjectsStats();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void getProjectMembers() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        String status = "accepted"; // Status can be accepted, not_accepted
        List<ProjectMemberResponse> response = litmusClient.getProjectMembers(projectID, status);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void getProjectOwners() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        List<ProjectMemberResponse> response = litmusClient.getProjectOwners(projectID);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void deleteProject() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        CommonResponse response = litmusClient.deleteProject(projectID);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void sendInvitation() throws IOException, LitmusApiException {
        SendInvitationRequest request = SendInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .role("Executor") // Role can be Owner, Executor, Viewer
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        SendInvitationResponse response = litmusClient.sendInvitation(request);
        assertThat(response).isInstanceOf(SendInvitationResponse.class);
    }

    @Test
    public void acceptInvitation() throws IOException, LitmusApiException {
        AcceptInvitationRequest request = AcceptInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.acceptInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void declineInvitation() throws IOException, LitmusApiException {
        DeclineInvitationRequest request = DeclineInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.declineInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void removeInvitation() throws IOException, LitmusApiException {
        RemoveInvitationRequest request = RemoveInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.removeInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void listInvitation() throws IOException, LitmusApiException {
        String status = "Accepted"; // Status can be Pending, Accepted, Declined, Exited
        List<ListInvitationResponse> response = litmusClient.listInvitation(status);
        assertThat(response).isInstanceOf(List.class);
    }

    // List of users that can be invited
    @Test
    public void inviteUsers() throws IOException, LitmusApiException {
        String projectId = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        List<InviteUsersResponse> response = litmusClient.inviteUsers(projectId);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void updateMemberRole() throws IOException, LitmusApiException {
        UpdateMemberRoleRequest request = UpdateMemberRoleRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .role("Executor") // Role can be Owner, Executor, Viewer
                .build();

        CommonResponse response = litmusClient.updateMemberRole(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void status() throws IOException, LitmusApiException {
        StatusResponse response = litmusClient.status();
        assertThat(response).isInstanceOf(StatusResponse.class);
    }

    @Test
    public void readiness() throws IOException, LitmusApiException {
        ReadinessResponse response = litmusClient.readiness();
        assertThat(response).isInstanceOf(ReadinessResponse.class);
    }
}
