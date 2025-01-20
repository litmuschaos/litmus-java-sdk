import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class Example {

    private static final String HOST_URL = "http://127.0.0.1:3000";
    private static final String TEST_TOKEN = "Bearer token";
    private static final Logger log = LoggerFactory.getLogger(Example.class);

    private MockedLitmusClient litmusClient;

    @BeforeEach
    public void setup() throws IOException, LitmusApiException {
        this.litmusClient = new MockedLitmusClient(HOST_URL,TEST_TOKEN);
    }

    @Test
    public void getTokens() throws IOException, LitmusApiException {
        String userId = "1234";
        ListTokensResponse response = litmusClient.getTokens(userId);
        assertThat(response.getTokens()).hasSize(1);
    }

    @Test
    public void createToken() throws IOException, LitmusApiException {
        TokenCreateRequest request = TokenCreateRequest.builder()
                .name("litmus")
                .build();

        TokenCreateResponse response = litmusClient.createToken(request);

        assertThat(response.getAccessToken()).isEqualTo("litmus token");
    }

    @Test
    public void deleteToken() throws IOException, LitmusApiException {
        TokenDeleteRequest request = TokenDeleteRequest.builder()
                .token("token")
                .userID("1234")
                .build();
        CommonResponse response = litmusClient.deleteToken(request);
        assertThat(response.getMessage()).isEqualTo("Token deleted successfully");
    }

    @Test
    public void getUser() throws IOException, LitmusApiException {
        String userId = "1234";

        UserResponse response = litmusClient.getUser(userId);

        assertThat(response.getUserID()).isEqualTo("1234");
        assertThat(response.getName()).isEqualTo("Mock Name");
    }

    @Test
    public void getUsers() throws IOException, LitmusApiException {
        List<UserResponse> response = litmusClient.getUsers();
        assertThat(response).hasSize(2);
    }

    @Test
    public void updatePassword() throws IOException, LitmusApiException {
        PasswordUpdateRequest request = PasswordUpdateRequest.builder().
                username("litmus").
                oldPassword("old password").
                newPassword("new password").
                build();

        PasswordUpdateResponse response = litmusClient.updatePassword(request);

        assertThat(response.getMessage()).isEqualTo("Password updated successfully");
    }

    @Test
    public void createUser() throws IOException, LitmusApiException {
        UserCreateRequest request = UserCreateRequest.builder()
                .username("litmus")
                .password("password")
                .role("admin")
                .email("email")
                .build();

        UserResponse response = litmusClient.createUser(request);
        assertThat(response.getUserID()).isEqualTo("mockUserId");

    }

    @Test
    public void resetPassword() throws IOException, LitmusApiException {
        PasswordResetRequest request = PasswordResetRequest.builder()
                .username("litmus")
                .newPassword("new password")
                .build();

        CommonResponse response = litmusClient.resetPassword(request);

        assertThat(response.getMessage()).isEqualTo("Password reset successfully");
    }

    @Test
    public void updateUserDetails() throws IOException, LitmusApiException {
        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .userID("1234")
                .name("new name")
                .email("new email")
                .build();

        CommonResponse response = litmusClient.updateUserDetails(request);

        assertThat(response.getMessage()).isEqualTo("User details updated successfully");
    }

    @Test
    public void updateUserState() throws IOException, LitmusApiException {
        UserStateUpdateRequest request = UserStateUpdateRequest.builder()
                .username("litmus")
                .isDeactivate(true)
                .build();

        CommonResponse response = litmusClient.updateUserState(request);

        assertThat(response.getMessage()).isEqualTo("User state updated successfully");
    }

    @Test
    public void listProjects() throws IOException, LitmusApiException {
        ListProjectRequest request = ListProjectRequest.builder()
                .page(0)
                .limit(10)
                .sortField("name")
                .createdByMe(true)
                .build();

        ListProjectsResponse response = litmusClient.listProjects(request);
        assertThat(response.getTotalNumberOfProjects()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void createProject() throws IOException, LitmusApiException {
        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectName("litmus")
                .description("litmus project")
                .tags(List.of("tag1", "tag2"))
                .build();

        ProjectResponse response = litmusClient.createProject(request);

        assertThat(response.getName()).isEqualTo("litmus");
    }

    @Test
    public void updateProjectName() throws IOException, LitmusApiException {
        ProjectNameRequest request = ProjectNameRequest.builder()
                .projectID("1234")
                .projectName("litmus")
                .build();

        CommonResponse response = litmusClient.updateProjectName(request);

        assertThat(response.getMessage()).isEqualTo("Project name updated successfully");
    }

    @Test
    public void getProject() throws IOException, LitmusApiException {
        String projectID = "1234";

        ProjectResponse response = litmusClient.getProject(projectID);

        assertThat(response.getProjectID()).isEqualTo("1234");
        assertThat(response.getName()).isEqualTo("litmus");
    }

    @Test
    public void deleteProject() throws IOException, LitmusApiException {
        String projectID = "1234";

        CommonResponse response = litmusClient.deleteProject(projectID);

        assertThat(response.getMessage()).isEqualTo("Project deleted successfully");
    }

    @Test
    public void leaveProject() throws IOException, LitmusApiException {
        LeaveProjectRequest request = LeaveProjectRequest.builder()
                .projectID("1234")
                .userID("1234")
                .build();

        CommonResponse response = litmusClient.leaveProject(request);

        assertThat(response.getMessage()).isEqualTo("User left project successfully");
    }

    @Test
    public void getProjectRole() throws IOException, LitmusApiException {
        String projectID = "1234";

        ProjectRoleResponse response = litmusClient.getProjectRole(projectID);

        assertThat(response.getRole()).isEqualTo("Viewer");
    }

    @Test
    public void getUserWithProject() throws IOException, LitmusApiException {

        String username = "litmus";
        UserWithProjectResponse response = litmusClient.getUserWithProject(username);

        assertThat(response.getUsername()).isEqualTo("litmus");
    }

    @Test
    public void getProjectsStats() throws IOException, LitmusApiException {
        List<ProjectsStatsResponse> response = litmusClient.getProjectsStats();

        assertThat(response).hasSize(2);
    }

    @Test
    public void getProjectMembers() throws IOException, LitmusApiException {
        String projectID = "1234";
        String status = "active";
        List<ProjectMemberResponse> response = litmusClient.getProjectMembers(projectID, status);

        assertThat(response).hasSize(2);
    }

    @Test
    public void getProjectOwners() throws IOException, LitmusApiException {
        String projectID = "1234";
        List<ProjectMemberResponse> response = litmusClient.getProjectOwners(projectID);

        assertThat(response).hasSize(2);
    }

}
