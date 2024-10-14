package io.litmuschaos;

import com.google.gson.reflect.TypeToken;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.CreateProjectRequest;
import io.litmuschaos.request.LeaveProjectRequest;
import io.litmuschaos.request.ListProjectRequest;
import io.litmuschaos.request.LoginRequest;
import io.litmuschaos.request.ProjectNameRequest;
import io.litmuschaos.response.CapabilityResponse;
import io.litmuschaos.response.CommonResponse;
import io.litmuschaos.response.ListProjectsResponse;
import io.litmuschaos.response.LoginResponse;

import io.litmuschaos.response.ProjectMemberResponse;
import io.litmuschaos.response.ProjectResponse;
import io.litmuschaos.response.ProjectRoleResponse;
import io.litmuschaos.response.ProjectsStatsResponse;
import io.litmuschaos.response.UserWithProjectResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LitmusClient implements AutoCloseable {

    private String token;
    private final LitmusHttpClient httpClient;

    public LitmusClient(String host, String username, String password)
            throws IOException, LitmusApiException {
        this.httpClient = new LitmusHttpClient(host);
        this.authenticate(username, password);
    }

    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    // TODO - @Suyeon Jung : host, port config to LitmusAuthConfig class
    public LoginResponse authenticate(String username, String password)
            throws IOException, LitmusApiException {
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = httpClient.post("/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return httpClient.get("/capabilities", CapabilityResponse.class);
    }

    public ListProjectsResponse listProjects(ListProjectRequest request)
            throws IOException, LitmusApiException {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put("page", String.valueOf(request.getPage()));
        requestParam.put("limit", String.valueOf(request.getLimit()));
        requestParam.put("sortField", request.getSortField());
        requestParam.put("createdByMe", String.valueOf(request.getCreatedByMe()));
        return httpClient.get("/list_projects", token, requestParam, ListProjectsResponse.class);
    }

    public ProjectResponse createProject(CreateProjectRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post("/create_project", token, request, ProjectResponse.class);
    }

    public CommonResponse updateProjectName(ProjectNameRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post("/update_project_name", token, request, CommonResponse.class);
    }

    public ProjectResponse getProject(String projectId) throws IOException, LitmusApiException {
        return httpClient.get("/get_project/" + projectId, token, ProjectResponse.class);
    }

    public List<ProjectResponse> getOwnerProjects() throws IOException, LitmusApiException {
        TypeToken<List<ProjectResponse>> typeToken = new TypeToken<List<ProjectResponse>>() {};
        return httpClient.get("/get_owner_projects", token, typeToken);
    }

    public CommonResponse leaveProject(LeaveProjectRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post("/leave_project", token, request, CommonResponse.class);
    }

    public ProjectRoleResponse getProjectRole(String projectId)
            throws IOException, LitmusApiException {
        return httpClient.get("/get_project_role/" + projectId, token, ProjectRoleResponse.class);
    }

    public UserWithProjectResponse getUserWithProject(String username)
            throws IOException, LitmusApiException {
        return httpClient.get("/get_user_with_project/" + username, token,
                UserWithProjectResponse.class);
    }

    public List<ProjectsStatsResponse> getProjectsStats() throws IOException, LitmusApiException {
        TypeToken<List<ProjectsStatsResponse>> typeToken = new TypeToken<List<ProjectsStatsResponse>>() {};
        return httpClient.get("/get_projects_stats", token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectMembers(String projectID, String status)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get("/get_project_members/" + projectID + "/" + status, token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectOwners(String projectID)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get("/get_project_owners/" + projectID, token, typeToken);
    }

    public CommonResponse deleteProject(String projectID) throws IOException, LitmusApiException {
        return httpClient.post("/delete_project/" + projectID, token, CommonResponse.class);
    }

}
