package io.litmuschaos;

import com.google.gson.reflect.TypeToken;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;

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
        LoginRequest request = LoginRequest.builder().username(username).password(password).build();
        this.authenticate(request);
    }

    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    public LoginResponse authenticate(LoginRequest request)
            throws IOException, LitmusApiException {
        LoginResponse response = httpClient.post("/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    public CommonResponse logout() throws IOException, LitmusApiException {
        CommonResponse commonResponse = httpClient.post("/logout", token, CommonResponse.class);
        this.token = "";
        return commonResponse;
    }

    public ListTokensResponse getTokens(String userId) throws IOException, LitmusApiException {
        return httpClient.get("/token/" + userId, token, ListTokensResponse.class);
    }

    public TokenCreateResponse createToken(TokenCreateRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/create_token", token, request, TokenCreateResponse.class);
    }

    public CommonResponse deleteToken(TokenDeleteRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/remove_token", token, request, CommonResponse.class);
    }

    public UserResponse getUser(String userId) throws IOException, LitmusApiException {
        return httpClient.get("/get_user/" + userId, token, UserResponse.class);
    }

    public List<UserResponse> getUsers() throws IOException, LitmusApiException {
        TypeToken<List<UserResponse>> typeToken = new TypeToken<>() {};
        return httpClient.get("/users", token, typeToken);
    }

    public PasswordUpdateResponse updatePassword(PasswordUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/update/password", token, request, PasswordUpdateResponse.class);
    }

    public UserResponse createUser(UserCreateRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/create_user", token, request, UserResponse.class);
    }

    public CommonResponse resetPassword(PasswordResetRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/reset/password", token, request, CommonResponse.class);
    }

    public CommonResponse updateUserDetails(UserDetailsUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/update/details", token, request, CommonResponse.class);
    }

    public CommonResponse updateUserState(UserStateUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/update/state", token, request, CommonResponse.class);
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
