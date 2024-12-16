package io.litmuschaos;

import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.client.*;
import io.litmuschaos.generated.types.*;
import io.litmuschaos.graphql.LitmusGraphQLClient;
import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LitmusClient implements AutoCloseable {

    private String token;
    private final LitmusHttpClient httpClient;
    private final LitmusGraphQLClient graphQLClient;

    public LitmusClient(String host, String token) {
        String sanitizedHost = sanitizeURL(host);
        OkHttpClient okHttpClient = new OkHttpClient();
        this.token = token;
        this.httpClient = new LitmusHttpClient(okHttpClient, sanitizedHost + "/auth");
        this.graphQLClient = new LitmusGraphQLClient(okHttpClient, sanitizedHost + "/api/query", this.token);
    }

    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    // User
//    public LoginResponse authenticate(LoginRequest request)
//            throws IOException, LitmusApiException {
//        LoginResponse response = httpClient.post("/login", request, LoginResponse.class);
//        this.token = response.getAccessToken();
//        return response;
//    }

//    public CommonResponse logout() throws IOException, LitmusApiException {
//        CommonResponse commonResponse = httpClient.post("/logout", token, CommonResponse.class);
//        this.token = "";
//        return commonResponse;
//    }

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
        TypeToken<List<UserResponse>> typeToken = new TypeToken<>() {
        };
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

    // Capabilities
    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return httpClient.get("/capabilities", CapabilityResponse.class);
    }

    // Project
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
        TypeToken<List<ProjectResponse>> typeToken = new TypeToken<List<ProjectResponse>>() {
        };
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
        TypeToken<List<ProjectsStatsResponse>> typeToken = new TypeToken<List<ProjectsStatsResponse>>() {
        };
        return httpClient.get("/get_projects_stats", token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectMembers(String projectID, String status)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {
        };
        return httpClient.get("/get_project_members/" + projectID + "/" + status, token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectOwners(String projectID)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {
        };
        return httpClient.get("/get_project_owners/" + projectID, token, typeToken);
    }

    public CommonResponse deleteProject(String projectID) throws IOException, LitmusApiException {
        return httpClient.post("/delete_project/" + projectID, token, CommonResponse.class);
    }

    public SendInvitationResponse sendInvitation(SendInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/send_invitation", token, request, SendInvitationResponse.class);
    }

    public CommonResponse acceptInvitation(AcceptInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/accept_invitation", token, request, CommonResponse.class);
    }

    public CommonResponse declineInvitation(DeclineInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/decline_invitation", token, request, CommonResponse.class);
    }

    public CommonResponse removeInvitation(RemoveInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post("/remove_invitation", token, request, CommonResponse.class);
    }

    public List<ListInvitationResponse> listInvitation(String status)
            throws IOException, LitmusApiException {
        return httpClient.get("/list_invitations_with_filters/" + status, token, List.class);
    }

    public List<InviteUsersResponse> inviteUsers(String projectId)
            throws IOException, LitmusApiException {
        return httpClient.get("/invite_users/" + projectId, token, List.class);
    }

    // Environment
    public Environment getEnvironment(GetEnvironmentGraphQLQuery query, GetEnvironmentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getEnvironment", new TypeRef<Environment>(){});
    }

    public ListEnvironmentResponse listEnvironments(ListEnvironmentsGraphQLQuery query, ListEnvironmentsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listEnvironments", new TypeRef<ListEnvironmentResponse>(){});
    }

    public Environment createEnvironment(CreateEnvironmentGraphQLQuery query, CreateEnvironmentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.createEnvironment", new TypeRef<Environment>() {});
    }

    public String deleteEnvironment(DeleteEnvironmentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.deleteEnvironment");
    }

    public String updateEnvironment(UpdateEnvironmentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.updateEnvironment");
    }

    // Chaos Infrastructure
    public Infra getInfra(GetInfraGraphQLQuery query, GetInfraProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getInfra", new TypeRef<Infra>(){});
    }

    public ListInfraResponse listInfras(ListInfrasGraphQLQuery query, ListInfrasProjectionRoot projectionRoot) {
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listInfras", new TypeRef<ListInfraResponse>() {});
    }

    public Infra getInfraDetails(GetInfraDetailsGraphQLQuery query, GetInfraDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getInfraDetails", new TypeRef<Infra>(){});
    }

    public GetInfraStatsResponse getInfraStats(GetInfraDetailsGraphQLQuery query, GetInfraDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getInfraStats", new TypeRef<GetInfraStatsResponse>(){});
    }

    public String getInfraManifest(GetInfraManifestGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.getInfraManifest");
    }

    public ConfirmInfraRegistrationResponse confirmInfraRegistration(ConfirmInfraRegistrationGraphQLQuery query, ConfirmInfraRegistrationProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.confirmInfraRegistration", new TypeRef<ConfirmInfraRegistrationResponse>(){});
    }

    public String deleteInfra(DeleteInfraGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.deleteInfra");
    }

    public RegisterInfraResponse registerInfra(RegisterInfraGraphQLQuery query, RegisterInfraProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.registerInfra", new TypeRef<RegisterInfraResponse>(){});
    }

    // Chaos Hub
    public List<ChaosHubStatus> listChaosHub(ListChaosHubGraphQLQuery query, ListChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listChaosHub", new TypeRef<List<ChaosHubStatus>>(){});
    }

    public ChaosHubStatus getChaosHub(GetChaosHubGraphQLQuery query, GetChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getChaosHub", new TypeRef<ChaosHubStatus>() {});
    }

    public GetChaosHubStatsResponse getChaosHubStats(GetChaosHubStatsGraphQLQuery query, GetChaosHubStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getChaosHubStats", new TypeRef<GetChaosHubStatsResponse>() {});
    }

    public ChaosHub addChaosHub(AddChaosHubGraphQLQuery query, AddChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.addChaosHub", new TypeRef<ChaosHub>(){});
    }

    public ChaosHub addRemoteChaosHub(AddRemoteChaosHubGraphQLQuery query, AddRemoteChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.addRemoteChaosHub", new TypeRef<ChaosHub>() {});
    }

    public Boolean deleteChaosHub(DeleteChaosHubGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.deleteChaosHub");
    }

    public ChaosHub saveChaosHub(SaveChaosHubGraphQLQuery query, SaveChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.saveChaosHub", new TypeRef<ChaosHub>() {});
    }

    public String syncChaosHub(SyncChaosHubGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.syncChaosHub");
    }

    public ChaosHub updateChaosHub(UpdateChaosHubGraphQLQuery query, UpdateChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.updateChaosHub", new TypeRef<ChaosHub>() {});
    }

    // Chaos Experiment
    public GetExperimentResponse getExperiment(GetExperimentGraphQLQuery query, GetExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getExperiment", new TypeRef<GetExperimentResponse>(){});
    }

    public ListExperimentResponse listExperiment(ListExperimentGraphQLQuery query, ListExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listExperiment", new TypeRef<ListExperimentResponse>(){});
    }

    public GetExperimentStatsResponse getExperimentStats(GetExperimentStatsGraphQLQuery query, GetExperimentStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getExperimentStats", new TypeRef<GetExperimentStatsResponse>(){});
    }

    public List<PredefinedExperimentList> getPredefinedExperiment(GetPredefinedExperimentGraphQLQuery query, GetPredefinedExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getPredefinedExperiment", new TypeRef<List<PredefinedExperimentList>>(){});
    }

    public List<PredefinedExperimentList> listPredefinedExperiments(ListPredefinedExperimentsGraphQLQuery query, ListPredefinedExperimentsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listPredefinedExperiments", new TypeRef<List<PredefinedExperimentList>>(){});
    }

    public RunChaosExperimentResponse runChaosExperiment(RunChaosExperimentGraphQLQuery query, RunChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.runChaosExperiment", new TypeRef<RunChaosExperimentResponse>(){});
    }

    public String saveChaosExperiment(SaveChaosExperimentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.saveChaosExperiment");
    }

    public ChaosExperimentResponse updateChaosExperiment(UpdateChaosExperimentGraphQLQuery query, UpdateChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.updateChaosExperiment", new TypeRef<ChaosExperimentResponse>(){});
    }

    public ChaosExperimentResponse createChaosExperiment(CreateChaosExperimentGraphQLQuery query, CreateChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.createChaosExperiment", new TypeRef<ChaosExperimentResponse>(){});
    }

    public Boolean deleteChaosExperiment(DeleteChaosExperimentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.deleteChaosExperiment");
    }

    public Boolean updateCronExperimentState(UpdateCronExperimentStateGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.updateCronExperimentState");
    }

    // Chaos Experiment Run
    public ExperimentRun getExperimentRun(GetExperimentRunGraphQLQuery query, GetExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getExperimentRun", new TypeRef<ExperimentRun>(){});
    }

    public GetExperimentRunStatsResponse getExperimentRunStats(GetExperimentRunStatsGraphQLQuery query, GetExperimentRunStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getExperimentRunStats", new TypeRef<GetExperimentRunStatsResponse>(){});
    }

    public ListExperimentRunResponse listExperimentRun(ListExperimentGraphQLQuery query, ListExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listExperimentRun", new TypeRef<ListExperimentRunResponse>(){});
    }

    public String chaosExperimentRun(ChaosExperimentRunGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.chaosExperimentRun");
    }

    public Boolean stopExperimentRuns(StopExperimentRunsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.stopExperimentRuns");
    }

    // GitOps
    public GitConfigResponse getGitOpsDetails(GetGitOpsDetailsGraphQLQuery query, GetGitOpsDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getGitOpsDetails", new TypeRef<GitConfigResponse>(){});
    }

    public Boolean disableGitOps(DisableGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.disableGitOps");
    }

    public Boolean enableGitOps(EnableGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.enableGitOps");
    }

    public String gitopsNotifier(GitopsNotifierGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.gitopsNotifier");
    }

    public Boolean updateGitOps(UpdateGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.updateGitOps");
    }

    // Image Registry
    public ImageRegistryResponse getImageRegistry(GetImageRegistryGraphQLQuery query, GetImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getImageRegistry", new TypeRef<ImageRegistryResponse>(){});
    }

    public List<ImageRegistryResponse> listImageRegistry(ListImageRegistryGraphQLQuery query, ListImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listImageRegistry", new TypeRef<List<ImageRegistryResponse>>(){});
    }

    public ImageRegistryResponse createImageRegistry(CreateImageRegistryGraphQLQuery query, CreateImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.createImageRegistry", new TypeRef<ImageRegistryResponse>(){});
    }

    public String deleteImageRegistry(DeleteImageRegistryGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.deleteImageRegistry");
    }

    public ImageRegistryResponse updateImageRegistry(UpdateImageRegistryGraphQLQuery query, UpdateImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.updateImageRegistry", new TypeRef<ImageRegistryResponse>(){});
    }
    // Probe
    public List<Probe> listProbes(ListProbesGraphQLQuery query, ListProbesProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.listProbes", new TypeRef<List<Probe>>(){});
    }

    public Probe getProbe(GetProbeGraphQLQuery query, GetProbeProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getProbe", new TypeRef<Probe>(){});
    }

    public Boolean validateUniqueProbe(ValidateUniqueProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.validateUniqueProbe");
    }

    public GetProbeReferenceResponse getProbeReference(GetProbeReferenceGraphQLQuery query, GetProbeReferenceProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getProbeReference", new TypeRef<GetProbeReferenceResponse>(){});
    }

    public String getProbeYAML(GetProbeYAMLGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.getProbeYAML");
    }

    public GetProbesInExperimentRunResponse getProbesInExperimentRun(GetProbesInExperimentRunGraphQLQuery query, GetProbesInExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getProbesInExperimentRun", new TypeRef<GetProbesInExperimentRunResponse>(){});
    }

    public Probe addProbe(AddProbeGraphQLQuery query, AddProbeProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.addProbe", new TypeRef<Probe>(){});
    }

    public Boolean deleteProbe(DeleteProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.deleteProbe");
    }

    public String updateProbe(UpdateProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.updateProbe");
    }

    // chaos fault
    public FaultDetails getChaosFault(GetChaosFaultGraphQLQuery query, GetChaosFaultProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getChaosFault", new TypeRef<FaultDetails>(){});
    }

    public List<Chart> listChaosFaults(ListChaosFaultsGraphQLQuery query, ListChaosFaultsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        System.out.println(request);
        GraphQLResponse response = graphQLClient.query(request);

        return response.extractValueAsObject("data.listChaosFaults", new TypeRef<List<Chart>>(){});
    }

    // others
    public ServerVersionResponse getServerVersion(GetServerVersionGraphQLQuery query, GetServerVersionProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getServerVersion", new TypeRef<ServerVersionResponse>(){});
    }

    public InfraVersionDetails getVersionDetails(GetVersionDetailsGraphQLQuery query, GetVersionDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getVersionDetails", new TypeRef<InfraVersionDetails>(){});
    }

    public SSHKey generateSSHKey(GenerateSSHKeyGraphQLQuery query, GenerateSSHKeyProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.generateSSHKey", new TypeRef<SSHKey>(){});
    }

    public String getManifestWithInfraID(GetManifestWithInfraIDGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.getManifestWithInfraID");
    }

    public String kubeNamespace(KubeNamespaceGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.kubeNamespace");
    }

    public String kubeObj(KubeObjGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.kubeObj");
    }

    public String podLog(PodLogGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValue("data.podLog");
    }

    // subscription
    public InfraEventResponse getInfraEvents(GetInfraEventsGraphQLQuery query, GetInfraEventsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getInfraEvents", new TypeRef<InfraEventResponse>(){});
    }

    public KubeNamespaceResponse getKubeNamespace(GetKubeNamespaceGraphQLQuery query, GetKubeNamespaceProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query,projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getKubeNamespace", new TypeRef<KubeNamespaceResponse>(){});
    }

    public KubeObjectResponse getKubeObject(GetKubeObjectGraphQLQuery query, GetKubeObjectProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getKubeObject", new TypeRef<KubeObjectResponse>(){});
    }

    public PodLogResponse getPodLog(GetPodLogGraphQLQuery query, GetPodLogProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.getPodLog", new TypeRef<PodLogResponse>(){});
    }

    public InfraActionResponse infraConnect(InfraConnectGraphQLQuery query, InfraConnectProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject("data.infraConnect", new TypeRef<InfraActionResponse>(){});
    }

    private String sanitizeURL(String url) {
        // TODO: need to add a validate URL without protocol
        // edge case: If you're calling a service from within Kubernetes, you don't need a protocol.
        return url.replaceAll("/$", "");
    }
}