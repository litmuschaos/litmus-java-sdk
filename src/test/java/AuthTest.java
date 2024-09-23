import com.google.gson.reflect.TypeToken;
import io.litmuschaos.LitmusClient;

import io.litmuschaos.response.ProjectMemberResponse;
import io.litmuschaos.response.ProjectsStatusResponse;
import java.io.IOException;
import java.util.List;


public class AuthTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Mean0610@";

    public static void main(String[] args) throws IOException {

        LitmusClient authClient = new LitmusClient(hostUrl, username, password);

        System.out.println("### capabilities test");
        var capabilities = authClient.capabilities();
        System.out.println(capabilities);

        System.out.println("### createProject test");
        var project = authClient.createProject("TEST_Project_1sdfddddfdfdffdffds2");
        System.out.println(project);

        var auth = authClient.authenticate(username, password);
        System.out.println("### refresh token test");
        System.out.println("access Token :: " + auth.getAccessToken());

        System.out.println("### getListProjects test");
        var listProjects = authClient.getListProjects();
        System.out.println(listProjects);

        System.out.println("### updateProjectName test");
        var projectName = authClient.updateProjectName(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3", "dfdfdfdf");
        System.out.println(projectName);

        System.out.println("### getProject test");
        var getProject = authClient.getProject(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3");
        System.out.println(getProject);

        System.out.println("### getOwnerProject test");
        var ownerProjects = authClient.getOwnerProject();
        System.out.println(ownerProjects);

        System.out.println("### leaveProject test");
        var LeaveProject = authClient.leaveProject(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3", "79c3506fgfgc-273e-4018-a062-ff2cc4fbb248");
        System.out.println(LeaveProject);

        System.out.println("### getProjectRole test");
        var projectRole = authClient.getProjectRole(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3");
        System.out.println(projectRole);

        System.out.println("### getUserWithProject test");
        var UserWithProject = authClient.getUserWithProject();
        System.out.println(UserWithProject);

        System.out.println("### getProjectsStats test");
        List<ProjectsStatusResponse> projectStatus = authClient.getProjectsStats();
        System.out.println(projectStatus);

        System.out.println("### getProjectMembers test");
        var projectMembers = authClient.getProjectMembers( "86572722-365b-45aa-8a8e-9e5058fdaf90", "active");
        System.out.println(projectMembers);
    }

}
