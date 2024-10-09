package io.litmuschaos;

import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.LoginRequest;
import io.litmuschaos.response.CapabilityResponse;
import io.litmuschaos.response.LoginResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LitmusClient implements AutoCloseable {

    private String token;
    private final LitmusHttpClient httpClient;

    public LitmusClient(String host, String username, String password) throws IOException, LitmusApiException {
        this.httpClient = new LitmusHttpClient(host);
        this.authenticate(username, password);
    }
    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    // TODO - @Suyeon Jung : host, port config to LitmusAuthConfig class
    public LoginResponse authenticate(String username, String password) throws IOException, LitmusApiException {
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = httpClient.post("/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    public String createProject(String projectName) throws IOException, LitmusApiException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post("/create_project", token,  request, String.class);
    }

    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return httpClient.get("/capabilities", CapabilityResponse.class);
    }

    public ProjectResponse createProject(String projectName) throws IOException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post("/create_project", token, request, ProjectResponse.class);
    }

    public ListProjectsResponse getListProjects() throws IOException {
        return httpClient.get("/list_projects", token, ListProjectsResponse.class);
    }

    public CommonResponse updateProjectName(String projectID, String projectName) throws IOException {
        ProjectNameRequest request = new ProjectNameRequest(projectID, projectName);
        return httpClient.post("/update_project_name", token, request, CommonResponse.class);
    }

    public ProjectResponse getProject(String projectId) throws IOException {
        return httpClient.get("/get_project/" + projectId, token, ProjectResponse.class);
    }

    public List<ProjectResponse> getOwnerProjects() throws IOException {
        TypeToken<List<ProjectResponse>> typeToken = new TypeToken<List<ProjectResponse>>() {};
        return httpClient.get("/get_owner_projects", token, typeToken);
    }

    public CommonResponse leaveProject(String projectID, String userID) throws IOException {
        LeaveProjectRequest request = new LeaveProjectRequest(projectID, userID);
        return httpClient.post("/leave_project", token, request, CommonResponse.class);
    }

    public ProjectRoleResponse getProjectRole(String projectId) throws IOException {
        return httpClient.get("/get_project_role/" + projectId, token, ProjectRoleResponse.class);
    }

    public UserWithProjectResponse getUserWithProject() throws IOException {
        return httpClient.get("/get_user_with_project/admin", token, UserWithProjectResponse.class);
    }

    public List<ProjectsStatsResponse> getProjectsStats() throws IOException {
        TypeToken<List<ProjectsStatsResponse>> typeToken = new TypeToken<List<ProjectsStatsResponse>>() {};
        return httpClient.get("/get_projects_stats", token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectMembers(String projectID, String status) throws IOException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get("/get_project_members/" + projectID + "/" + status, token, typeToken);
    }

}
