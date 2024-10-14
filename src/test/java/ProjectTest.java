import static org.assertj.core.api.Assertions.assertThat;

import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.CreateProjectRequest;
import io.litmuschaos.request.LeaveProjectRequest;
import io.litmuschaos.request.ListProjectRequest;
import io.litmuschaos.request.ProjectNameRequest;
import io.litmuschaos.response.CommonResponse;
import io.litmuschaos.response.ListProjectsResponse;
import io.litmuschaos.response.ProjectMemberResponse;
import io.litmuschaos.response.ProjectResponse;
import io.litmuschaos.response.ProjectRoleResponse;
import io.litmuschaos.response.ProjectsStatsResponse;
import io.litmuschaos.response.UserWithProjectResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() throws IOException, LitmusApiException {
        this.litmusClient = new LitmusClient(hostUrl, username, password);
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

        assertThat(response.getTotalNumberOfProjects())
                .isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testCreateProject() throws IOException, LitmusApiException {

        String projectName = "Test Project-2";
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
        String projectID = "63488419-fc5c-4a1a-8aa9-4e736e8b3a73";
        String projectName = "new project name!";

        ProjectNameRequest request = ProjectNameRequest.builder()
                .projectID(projectID)
                .projectName(projectName)
                .build();

        CommonResponse response = litmusClient.updateProjectName(request);

        assertThat(response.getMessage()).isEqualTo("Successful");
    }


    @Test
    public void testGetProject() throws IOException, LitmusApiException {
        String projectId = "537f6a86-f1fd-4312-ab89-230b65ea6c73";

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
                .projectID("9ea7420a-531a-4a61-a75f-ed4aae4a925f")
                .userID("1141d7ab-21b7-4e0a-a043-ba3b08f54ce8")
                .build();

        CommonResponse response = litmusClient.leaveProject(request);
        assertThat(response.getMessage()).isEqualTo("Successful");
    }


    @Test
    public void testGetProjectRole() throws IOException, LitmusApiException {
        String projectId = "63488419-fc5c-4a1a-8aa9-4e736e8b3a73";

        ProjectRoleResponse response = litmusClient.getProjectRole(projectId);

        assertThat(response).isNotNull();
        assertThat(response.getRole()).isEqualTo("Owner");
    }


    @Test
    public void testGetUserWithProject() throws IOException, LitmusApiException {
        String testUsername = "admin";

        UserWithProjectResponse response = litmusClient.getUserWithProject(testUsername);

        assertThat(response.getUsername()).isEqualTo(testUsername);
        assertThat(response.getProjects()).isNotNull();
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
        String projectID = "63488419-fc5c-4a1a-8aa9-4e736e8b3a73";
        String status = "active";

        List<ProjectMemberResponse> response = litmusClient.getProjectMembers(projectID, status);

        assertThat(response.size()).isGreaterThanOrEqualTo(1);

        ProjectMemberResponse firstMember = response.get(0);
        assertThat(firstMember.getUserID()).isNotNull();
        assertThat(firstMember.getUsername()).isNotNull();

        if (firstMember.getDeactivatedAt() != null) {
            assertThat(firstMember.getDeactivatedAt()).isGreaterThan(0L);
        }
    }

    @Test
    public void testGetProjectOwners() throws IOException, LitmusApiException {
        String projectID = "63488419-fc5c-4a1a-8aa9-4e736e8b3a73";

        List<ProjectMemberResponse> response = litmusClient.getProjectOwners(projectID);

        assertThat(response.size()).isGreaterThan(0);
        ProjectMemberResponse firstOwner = response.get(0);
        assertThat(firstOwner.getUserID()).isNotNull();
        assertThat(firstOwner.getUsername()).isNotNull();
        assertThat(firstOwner.getRole()).isEqualTo("Owner");

    }

    @Test
    public void testDeleteProject() throws IOException, LitmusApiException {
        String projectID = "63488419-fc5c-4a1a-8aa9-4e736e8b3a73";

        CommonResponse response = litmusClient.deleteProject(projectID);

        assertThat(response.getMessage()).isEqualTo("Successfully deleted project.");
    }

}
