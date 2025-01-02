import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.CreateProjectRequest;
import io.litmuschaos.request.LeaveProjectRequest;
import io.litmuschaos.request.ListProjectRequest;
import io.litmuschaos.request.ProjectNameRequest;
import io.litmuschaos.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {

    private static final String HOST_URL = "http://localhost:3000";
    private static final String TEST_TOKEN = "Bear token"; // Put your token here
    private static LitmusClient litmusClient;
    private static String projectId;

    @BeforeAll
    public static void setupClient() {
        litmusClient = new LitmusClient(HOST_URL, TEST_TOKEN);
    }

    @BeforeEach
    public void setupProject() throws IOException, LitmusApiException {
        projectId = createProjectAndGetId();
    }

    private static String generateUniqueProjectName(String baseName) {
        return baseName + "-" + UUID.randomUUID().toString();
    }

    private static String createProjectAndGetId() throws IOException, LitmusApiException {
        String projectName = generateUniqueProjectName("Temporary Project");
        String description = "This is a temporary project.";
        List<String> tags = Arrays.asList("tag1", "tag2");

        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectName(projectName)
                .description(description)
                .tags(tags)
                .build();

        ProjectResponse response = litmusClient.createProject(request);
        return response.getProjectID();
    }

    @Test
    public void testGetListProjects() throws IOException, LitmusApiException {
        int page = 0;
        int limit = 10;
        String sortField = "name";
        boolean createdByMe = true;

        ListProjectRequest request = ListProjectRequest.builder()
                .page(page)
                .limit(limit)
                .sortField(sortField)
                .createdByMe(createdByMe)
                .build();

        ListProjectsResponse response = litmusClient.listProjects(request);

        assertThat(response.getTotalNumberOfProjects()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testCreateProject() throws IOException, LitmusApiException {
        String projectName = generateUniqueProjectName("Test Project");
        String description = "This is a test project.";
        List<String> tags = Arrays.asList("tag1", "tag2");

        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectName(projectName)
                .description(description)
                .tags(tags)
                .build();

        ProjectResponse response = litmusClient.createProject(request);

        assertThat(response.getProjectID()).isNotNull();
        assertThat(response.getName()).isEqualTo(projectName);
    }

    @Test
    public void testUpdateProjectName() throws IOException, LitmusApiException {
        String projectName = generateUniqueProjectName("new project name");

        ProjectNameRequest request = ProjectNameRequest.builder()
                .projectID(projectId)
                .projectName(projectName)
                .build();

        CommonResponse response = litmusClient.updateProjectName(request);

        assertThat(response.getMessage()).isEqualTo("Successful");
    }

    @Test
    public void testGetProject() throws IOException, LitmusApiException {
        ProjectResponse response = litmusClient.getProject(projectId);

        assertThat(response.getProjectID()).isEqualTo(projectId);
        assertThat(response.getName()).isNotNull();
    }

    @Test
    public void testGetOwnerProjects() throws IOException, LitmusApiException {
        List<ProjectResponse> ownerProjects = litmusClient.getOwnerProjects();

        ProjectResponse firstProject = ownerProjects.get(0);
        assertThat(firstProject.getProjectID()).isNotNull();
        assertThat(firstProject.getName()).isNotNull();
    }

    @Test
    public void leaveProjectTest() throws LitmusApiException, IOException {
        LeaveProjectRequest request = LeaveProjectRequest.builder()
                .projectID(projectId)
                .userID(litmusClient.getProject(projectId).getMembers().get(0).getUserID())
                .build();

        CommonResponse response = litmusClient.leaveProject(request);

        assertThat(response.getMessage()).isEqualTo("Successful");
    }

    @Test
    public void testGetProjectRole() throws IOException, LitmusApiException {
        ProjectRoleResponse response = litmusClient.getProjectRole(projectId);

        assertThat(response.getRole()).isEqualTo("Owner");
    }

    @Test
    public void testGetUserWithProject() throws IOException, LitmusApiException {
        String testUsername = "admin";

        UserWithProjectResponse response = litmusClient.getUserWithProject(testUsername);

        assertThat(response.getUsername()).isEqualTo(testUsername);
        assertThat(response.getProjects().size()).isGreaterThanOrEqualTo(1);

        ProjectResponse firstProject = response.getProjects().get(0);
        assertThat(firstProject.getProjectID()).isNotNull();
        assertThat(firstProject.getName()).isNotNull();
    }

    @Test
    public void testGetProjectsStats() throws IOException, LitmusApiException {
        List<ProjectsStatsResponse> response = litmusClient.getProjectsStats();

        assertThat(response.size()).isGreaterThanOrEqualTo(1);

        ProjectsStatsResponse firstProject = response.get(0);
        assertThat(firstProject.getProjectID()).isNotNull();
        assertThat(firstProject.getName()).isNotNull();
        assertThat(firstProject.getMembers().getTotal()).isGreaterThanOrEqualTo(1);

        ProjectsStatsResponse.Members members = firstProject.getMembers();
        assertThat(members.getOwner().size()).isGreaterThanOrEqualTo(1);

        ProjectsStatsResponse.Owner firstOwner = members.getOwner().get(0);
        assertThat(firstOwner.getUserID()).isNotNull();
        assertThat(firstOwner.getUsername()).isNotNull();
    }

    @Test
    public void testGetProjectMembers() throws IOException, LitmusApiException {
        String status = "active";

        List<ProjectMemberResponse> response = litmusClient.getProjectMembers(projectId, status);

        assertThat(response.size()).isGreaterThanOrEqualTo(1);
        ProjectMemberResponse firstMember = response.get(0);
        assertThat(firstMember.getUserID()).isNotNull();
        assertThat(firstMember.getUsername()).isNotNull();

    }

    @Test
    public void testGetProjectOwners() throws IOException, LitmusApiException {
        List<ProjectMemberResponse> response = litmusClient.getProjectOwners(projectId);

        assertThat(response.size()).isGreaterThanOrEqualTo(1);
        ProjectMemberResponse firstOwner = response.get(0);
        assertThat(firstOwner.getUserID()).isNotNull();
        assertThat(firstOwner.getUsername()).isNotNull();
        assertThat(firstOwner.getRole()).isEqualTo("Owner");
    }

    @Test
    public void testDeleteProject() throws IOException, LitmusApiException {
        CommonResponse response = litmusClient.deleteProject(projectId);

        assertThat(response.getMessage()).isEqualTo("Successfully deleted project.");
    }
}
