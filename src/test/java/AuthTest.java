import io.litmuschaos.LitmusClient;

import java.io.IOException;

public class AuthTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    public static void main(String[] args) throws IOException {

        LitmusClient authClient = new LitmusClient(hostUrl, username, password);

        System.out.println("### capabilities test");
        var capabilities = authClient.capabilities();
        System.out.println(capabilities);

        System.out.println("### createProject test");
        var createProject = authClient.createProject("TEST_Project");
        System.out.println(createProject);

        var auth = authClient.authenticate(username, password);
        System.out.println("### refresh token test");
        System.out.println("access Token :: " + auth.getAccessToken());

        System.out.println("### getListProjects test");
        var listProjects = authClient.getListProjects();
        System.out.println(listProjects);

        System.out.println("### updateProjectName test");
        var projectName = authClient.updateProjectName(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3", "new project name");
        System.out.println(projectName);

        System.out.println("### getProject test");
        var getProject = authClient.getProject("db8d1fc2-c8f4-413f-a833-56c1978be3c3");
        System.out.println(getProject);

        System.out.println("### getOwnerProject test");
        var ownerProjects = authClient.getOwnerProjects();
        System.out.println(ownerProjects);

        System.out.println("### leaveProject test");
        var leaveProject = authClient.leaveProject(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3", "79c3506fgfgc-273e-4018-a062-ff2cc4fbb248");
        System.out.println(leaveProject);

        System.out.println("### getProjectRole test");
        var projectRole = authClient.getProjectRole(
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3");
        System.out.println(projectRole);

        System.out.println("### getUserWithProject test");
        var userWithProject = authClient.getUserWithProject();
        System.out.println(userWithProject);

        System.out.println("### getProjectsStats test");
        var projectStats = authClient.getProjectsStats();
        System.out.println(projectStats);

        System.out.println("### getProjectMembers test");
        var projectMembers = authClient.getProjectMembers(
                "86572722-365b-45aa-8a8e-9e5058fdaf90", "active");
        System.out.println(projectMembers);
    }

}
