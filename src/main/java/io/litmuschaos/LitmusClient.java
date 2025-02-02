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

import static io.litmuschaos.constants.ApiEndpoints.*;
import static io.litmuschaos.constants.OperationNames.*;
import static io.litmuschaos.constants.RequestParams.*;

/**
 * @author LitmusChaos Team
 * @version 1.0.0
 * @see <a href="https://litmuschaos.github.io/litmus/experiments/api/contents/">LitmusChaos API Docs</a>
 * , <a href="https://github.com/litmuschaos/litmus-java-sdk/blob/master/src/test/java/Example.java">Example</a>
 */
public class LitmusClient implements AutoCloseable {

    private String token;
    private final LitmusHttpClient httpClient;
    private final LitmusGraphQLClient graphQLClient;

    public LitmusClient(String host, String token) {
        String sanitizedHost = sanitizeURL(host);
        OkHttpClient okHttpClient = new OkHttpClient();
        this.token = token;
        this.httpClient = new LitmusHttpClient(okHttpClient, sanitizedHost + AUTH);
        this.graphQLClient = new LitmusGraphQLClient(okHttpClient, sanitizedHost + API_QUERY, this.token);
    }

    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    /**
     * Returns all the api tokens for the user.
     *
     * @param userId
     * @return Returns a list of tokens.
     * @throws IOException
     * @throws LitmusApiException
     */
    public ListTokensResponse getTokens(String userId) throws IOException, LitmusApiException {
        return httpClient.get(GET_TOKENS + "/" + userId, token, ListTokensResponse.class);
    }

    /**
     * Creates a new api token for the user.
     *
     * @param request
     * @return Returns the created token.
     * @throws IOException
     * @throws LitmusApiException
     */
    public TokenCreateResponse createToken(TokenCreateRequest request) throws IOException, LitmusApiException {
        return httpClient.post(CREATE_TOKEN, token, request, TokenCreateResponse.class);
    }

    /**
     * Delete api token for the user.
     *
     * @param request
     * @return return the response of the delete request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse deleteToken(TokenDeleteRequest request) throws IOException, LitmusApiException {
        return httpClient.post(REMOVE_TOKEN, token, request, CommonResponse.class);
    }

    /**
     * Get the user details.
     *
     * @param userId
     * @return Returns the user details.
     * @throws IOException
     * @throws LitmusApiException
     */
    public UserResponse getUser(String userId) throws IOException, LitmusApiException {
        return httpClient.get(GET_USER + "/" + userId, token, UserResponse.class);
    }

    /**
     * Get
     * Fetch users.
     *
     * @return Returns the list of users.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<UserResponse> getUsers() throws IOException, LitmusApiException {
        TypeToken<List<UserResponse>> typeToken = new TypeToken<>() {
        };
        return httpClient.get(GET_USERS, token, typeToken);
    }

    /**
     * Update the user password.
     *
     * @param request
     * @return Returns the projectID with message.
     * @throws IOException
     * @throws LitmusApiException
     */
    public PasswordUpdateResponse updatePassword(PasswordUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post(UPDATE_PASSWORD, token, request, PasswordUpdateResponse.class);
    }

    /**
     * Create a new user.
     *
     * @param request
     * @return return the user details.
     * @throws IOException
     * @throws LitmusApiException
     */
    public UserResponse createUser(UserCreateRequest request) throws IOException, LitmusApiException {
        return httpClient.post(CREATE_USER, token, request, UserResponse.class);
    }

    /**
     * Reset the user password.
     *
     * @param request
     * @return return the response of the reset password request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse resetPassword(PasswordResetRequest request) throws IOException, LitmusApiException {
        return httpClient.post(RESET_PASSWORD, token, request, CommonResponse.class);
    }

    /**
     * Update the user details.
     *
     * @param request
     * @return return the response of the update user details request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse updateUserDetails(UserDetailsUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post(UPDATE_USER_DETAILS, token, request, CommonResponse.class);
    }

    /**
     * Update the user state.
     *
     * @param request
     * @return return the response of the update user state request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse updateUserState(UserStateUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post(UPDATE_USER_STATE, token, request, CommonResponse.class);
    }

    // Capabilities
    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return httpClient.get(CAPABILITIES, CapabilityResponse.class);
    }

    // Project
    public ListProjectsResponse listProjects(ListProjectRequest request)
            throws IOException, LitmusApiException {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put(PAGE, String.valueOf(request.getPage()));
        requestParam.put(LIMIT, String.valueOf(request.getLimit()));
        requestParam.put(SORT_FIELD, request.getSortField());
        requestParam.put(CREATED_BY_ME, String.valueOf(request.getCreatedByMe()));
        return httpClient.get(LIST_PROJECTS, token, requestParam, ListProjectsResponse.class);
    }

    public ProjectResponse createProject(CreateProjectRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post(CREATE_PROJECT, token, request, ProjectResponse.class);
    }

    public CommonResponse updateProjectName(ProjectNameRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post(UPDATE_PROJECT_NAME, token, request, CommonResponse.class);
    }

    public ProjectResponse getProject(String projectId) throws IOException, LitmusApiException {
        return httpClient.get(GET_PROJECT + "/" + projectId, token, ProjectResponse.class);
    }

    public List<ProjectResponse> getOwnerProjects() throws IOException, LitmusApiException {
        TypeToken<List<ProjectResponse>> typeToken = new TypeToken<List<ProjectResponse>>() {};
        return httpClient.get(GET_OWNER_PROJECTS, token, typeToken);
    }

    public CommonResponse leaveProject(LeaveProjectRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post(LEAVE_PROJECT, token, request, CommonResponse.class);
    }

    public ProjectRoleResponse getProjectRole(String projectId)
            throws IOException, LitmusApiException {
        return httpClient.get(GET_PROJECT_ROLE + "/" + projectId, token, ProjectRoleResponse.class);
    }

    public UserWithProjectResponse getUserWithProject(String username)
            throws IOException, LitmusApiException {
        return httpClient.get(GET_USER_WITH_PROJECT + "/" + username, token,
                UserWithProjectResponse.class);
    }

    public List<ProjectsStatsResponse> getProjectsStats() throws IOException, LitmusApiException {
        TypeToken<List<ProjectsStatsResponse>> typeToken = new TypeToken<List<ProjectsStatsResponse>>() {};
        return httpClient.get(GET_PROJECTS_STATS, token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectMembers(String projectID, String status)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get(GET_PROJECT_MEMBERS + "/" + projectID + "/" + status, token, typeToken);
    }

    public List<ProjectMemberResponse> getProjectOwners(String projectID)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get(GET_PROJECT_OWNERS + "/" + projectID, token, typeToken);
    }

    public CommonResponse deleteProject(String projectID) throws IOException, LitmusApiException {
        return httpClient.post(DELETE_PROJECT + "/" + projectID, token, CommonResponse.class);
    }

    public SendInvitationResponse sendInvitation(SendInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(SEND_INVITATION, token, request, SendInvitationResponse.class);
    }

    public CommonResponse acceptInvitation(AcceptInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(ACCEPT_INVITATION, token, request, CommonResponse.class);
    }

    public CommonResponse declineInvitation(DeclineInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(DECLINE_INVITATION, token, request, CommonResponse.class);
    }

    public CommonResponse removeInvitation(RemoveInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(REMOVE_INVITATION, token, request, CommonResponse.class);
    }

    public List<ListInvitationResponse> listInvitation(String status)
            throws IOException, LitmusApiException {
        return httpClient.get(LIST_INVITATIONS_WITH_FILTERS + "/" + status, token, List.class);
    }

    public List<InviteUsersResponse> inviteUsers(String projectId)
            throws IOException, LitmusApiException {
        return httpClient.get(INVITE_USERS + "/" + projectId, token, List.class);
    }

    public CommonResponse updateMemberRole(UpdateMemberRoleRequest request) throws IOException, LitmusApiException{
        return httpClient.post(UPDATE_MEMBER_ROLE, token, request, CommonResponse.class);
    }

    // Misc
    public StatusResponse status() throws IOException, LitmusApiException {
        return httpClient.get(STATUS, token, StatusResponse.class);
    }

    public ReadinessResponse readiness() throws IOException, LitmusApiException {
        return httpClient.get(READINESS, token, ReadinessResponse.class);
    }

    // Environment
    public Environment getEnvironment(GetEnvironmentGraphQLQuery query, GetEnvironmentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_ENVIRONMENT, new TypeRef<Environment>(){});
    }

    public ListEnvironmentResponse listEnvironments(ListEnvironmentsGraphQLQuery query, ListEnvironmentsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_ENVIRONMENTS, new TypeRef<ListEnvironmentResponse>(){});
    }

    public Environment createEnvironment(CreateEnvironmentGraphQLQuery query, CreateEnvironmentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CREATE_ENVIRONMENT, new TypeRef<Environment>(){});
    }

    public DeleteEnvironmentResponse deleteEnvironment(DeleteEnvironmentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_ENVIRONMENT, new TypeRef<DeleteEnvironmentResponse>(){});
    }

    public UpdateEnvironmentResponse updateEnvironment(UpdateEnvironmentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_ENVIRONMENT, new TypeRef<UpdateEnvironmentResponse>(){});
    }

    // Chaos Infrastructure
    public Infra getInfra(GetInfraGraphQLQuery query, GetInfraProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA, new TypeRef<Infra>(){});
    }

    public ListInfraResponse listInfras(ListInfrasGraphQLQuery query, ListInfrasProjectionRoot projectionRoot) {
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_INFRAS, new TypeRef<ListInfraResponse>() {});
    }

    public Infra getInfraDetails(GetInfraDetailsGraphQLQuery query, GetInfraDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA_DETAILS, new TypeRef<Infra>(){});
    }

    public GetInfraStatsResponse getInfraStats(GetInfraStatsGraphQLQuery query, GetInfraStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA_STATS, new TypeRef<GetInfraStatsResponse>(){});
    }

    public GetInfraManifestResponse getInfraManifest(GetInfraManifestGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA_MANIFEST, new TypeRef<GetInfraManifestResponse>(){});
    }

    public ConfirmInfraRegistrationResponse confirmInfraRegistration(ConfirmInfraRegistrationGraphQLQuery query, ConfirmInfraRegistrationProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CONFIRM_INFRA_REGISTRATION, new TypeRef<ConfirmInfraRegistrationResponse>(){});
    }

    public DeleteInfraResponse deleteInfra(DeleteInfraGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_INFRA, new TypeRef<DeleteInfraResponse>(){});
    }

    public RegisterInfraResponse registerInfra(RegisterInfraGraphQLQuery query, RegisterInfraProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(REGISTER_INFRA, new TypeRef<RegisterInfraResponse>(){});
    }

    // Chaos Hub
    public List<ChaosHubStatus> listChaosHub(ListChaosHubGraphQLQuery query, ListChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_CHAOS_HUB, new TypeRef<List<ChaosHubStatus>>(){});
    }

    public ChaosHubStatus getChaosHub(GetChaosHubGraphQLQuery query, GetChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_CHAOS_HUB, new TypeRef<ChaosHubStatus>() {});
    }

    public GetChaosHubStatsResponse getChaosHubStats(GetChaosHubStatsGraphQLQuery query, GetChaosHubStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_CHAOS_HUB_STATS, new TypeRef<GetChaosHubStatsResponse>() {});
    }

    public ChaosHub addChaosHub(AddChaosHubGraphQLQuery query, AddChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ADD_CHAOS_HUB, new TypeRef<ChaosHub>(){});
    }

    public ChaosHub addRemoteChaosHub(AddRemoteChaosHubGraphQLQuery query, AddRemoteChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ADD_REMOTE_CHAOS_HUB, new TypeRef<ChaosHub>() {});
    }

    public DeleteChaosHubResponse deleteChaosHub(DeleteChaosHubGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_CHAOS_HUB, new TypeRef<DeleteChaosHubResponse>(){});
    }

    public ChaosHub saveChaosHub(SaveChaosHubGraphQLQuery query, SaveChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(SAVE_CHAOS_HUB, new TypeRef<ChaosHub>() {});
    }

    public SyncChaosHubResponse syncChaosHub(SyncChaosHubGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(SYNC_CHAOS_HUB, new TypeRef<SyncChaosHubResponse>() {});
    }

    public ChaosHub updateChaosHub(UpdateChaosHubGraphQLQuery query, UpdateChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_CHAOS_HUB, new TypeRef<ChaosHub>() {});
    }

    // Chaos Experiment
    public GetExperimentResponse getExperiment(GetExperimentGraphQLQuery query, GetExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT, new TypeRef<GetExperimentResponse>(){});
    }

    public ListExperimentResponse listExperiment(ListExperimentGraphQLQuery query, ListExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_EXPERIMENT, new TypeRef<ListExperimentResponse>(){});
    }

    public GetExperimentStatsResponse getExperimentStats(GetExperimentStatsGraphQLQuery query, GetExperimentStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT_STATS, new TypeRef<GetExperimentStatsResponse>(){});
    }

    public List<PredefinedExperimentList> getPredefinedExperiment(GetPredefinedExperimentGraphQLQuery query, GetPredefinedExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PREDEFINED_EXPERIMENT, new TypeRef<List<PredefinedExperimentList>>(){});
    }

    public List<PredefinedExperimentList> listPredefinedExperiments(ListPredefinedExperimentsGraphQLQuery query, ListPredefinedExperimentsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_PREDEFINED_EXPERIMENTS, new TypeRef<List<PredefinedExperimentList>>(){});
    }

    public RunChaosExperimentResponse runChaosExperiment(RunChaosExperimentGraphQLQuery query, RunChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(RUN_CHAOS_EXPERIMENT, new TypeRef<RunChaosExperimentResponse>(){});
    }

    public SaveChaosExperimentResponse saveChaosExperiment(SaveChaosExperimentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(SAVE_CHAOS_EXPERIMENT, new TypeRef<SaveChaosExperimentResponse>(){});
    }

    public ChaosExperimentResponse updateChaosExperiment(UpdateChaosExperimentGraphQLQuery query, UpdateChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_CHAOS_EXPERIMENT, new TypeRef<ChaosExperimentResponse>(){});
    }

    public ChaosExperimentResponse createChaosExperiment(CreateChaosExperimentGraphQLQuery query, CreateChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CREATE_CHAOS_EXPERIMENT, new TypeRef<ChaosExperimentResponse>(){});
    }

    public DeleteChaosExperimentResponse deleteChaosExperiment(DeleteChaosExperimentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_CHAOS_EXPERIMENT, new TypeRef<DeleteChaosExperimentResponse>(){});
    }

    public UpdateCronExperimentStateResponse updateCronExperimentState(UpdateCronExperimentStateGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_CRON_EXPERIMENT_STATE, new TypeRef<UpdateCronExperimentStateResponse>(){});
    }

    // Chaos Experiment Run
    public ExperimentRun getExperimentRun(GetExperimentRunGraphQLQuery query, GetExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT_RUN, new TypeRef<ExperimentRun>(){});
    }

    public GetExperimentRunStatsResponse getExperimentRunStats(GetExperimentRunStatsGraphQLQuery query, GetExperimentRunStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT_RUN_STATS, new TypeRef<GetExperimentRunStatsResponse>(){});
    }

    public ListExperimentRunResponse listExperimentRun(ListExperimentRunGraphQLQuery query, ListExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_EXPERIMENT_RUN, new TypeRef<ListExperimentRunResponse>(){});
    }

    public ChaosExperimentRunResponse chaosExperimentRun(ChaosExperimentRunGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CHAOS_EXPERIMENT_RUN, new TypeRef<ChaosExperimentRunResponse>(){});
    }

    public StopExperimentRunsResponse stopExperimentRuns(StopExperimentRunsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(STOP_EXPERIMENT_RUNS, new TypeRef<StopExperimentRunsResponse>(){});
    }

    // GitOps
    public GitConfigResponse getGitOpsDetails(GetGitOpsDetailsGraphQLQuery query, GetGitOpsDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_GIT_OPS_DETAILS, new TypeRef<GitConfigResponse>(){});
    }

    public DisableGitOpsResponse disableGitOps(DisableGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DISABLE_GIT_OPS, new TypeRef<DisableGitOpsResponse>(){});
    }

    public EnableGitOpsResponse enableGitOps(EnableGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ENABLE_GIT_OPS, new TypeRef<EnableGitOpsResponse>(){});
    }

    public GitOpsNotifierResponse gitopsNotifier(GitopsNotifierGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GITOPS_NOTIFIER, new TypeRef<GitOpsNotifierResponse>(){});
    }

    public UpdateGitOpsResponse updateGitOps(UpdateGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_GIT_OPS, new TypeRef<UpdateGitOpsResponse>(){});
    }

    // Image Registry
    public ImageRegistryResponse getImageRegistry(GetImageRegistryGraphQLQuery query, GetImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_IMAGE_REGISTRY, new TypeRef<ImageRegistryResponse>(){});
    }

    public List<ImageRegistryResponse> listImageRegistry(ListImageRegistryGraphQLQuery query, ListImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_IMAGE_REGISTRY, new TypeRef<List<ImageRegistryResponse>>(){});
    }

    public ImageRegistryResponse createImageRegistry(CreateImageRegistryGraphQLQuery query, CreateImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CREATE_IMAGE_REGISTRY, new TypeRef<ImageRegistryResponse>(){});
    }

    public DeleteImageRegistryResponse deleteImageRegistry(DeleteImageRegistryGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_IMAGE_REGISTRY, new TypeRef<DeleteImageRegistryResponse>(){});
    }

    public ImageRegistryResponse updateImageRegistry(UpdateImageRegistryGraphQLQuery query, UpdateImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_IMAGE_REGISTRY, new TypeRef<ImageRegistryResponse>(){});
    }

    // Probe
    public List<Probe> listProbes(ListProbesGraphQLQuery query, ListProbesProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_PROBES, new TypeRef<List<Probe>>(){});
    }

    public Probe getProbe(GetProbeGraphQLQuery query, GetProbeProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBE, new TypeRef<Probe>(){});
    }

    public ValidateUniqueProbeResponse validateUniqueProbe(ValidateUniqueProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(VALIDATE_UNIQUE_PROBE, new TypeRef<ValidateUniqueProbeResponse>(){});
    }

    public GetProbeReferenceResponse getProbeReference(GetProbeReferenceGraphQLQuery query, GetProbeReferenceProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBE_REFERENCE, new TypeRef<GetProbeReferenceResponse>(){});
    }

    public GetProbeYAMLResponse getProbeYAML(GetProbeYAMLGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBE_YAML, new TypeRef<GetProbeYAMLResponse>(){});
    }

    public List<GetProbesInExperimentRunResponse> getProbesInExperimentRun(GetProbesInExperimentRunGraphQLQuery query, GetProbesInExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBES_IN_EXPERIMENT_RUN, new TypeRef<List<GetProbesInExperimentRunResponse>>(){});
    }

    public Probe addProbe(AddProbeGraphQLQuery query, AddProbeProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ADD_PROBE, new TypeRef<Probe>(){});
    }

    public DeleteProbeResponse deleteProbe(DeleteProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_PROBE, new TypeRef<DeleteProbeResponse>(){});
    }

    public UpdateProbeResponse updateProbe(UpdateProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_PROBE, new TypeRef<UpdateProbeResponse>(){});
    }

    // chaos fault
    public FaultDetails getChaosFault(GetChaosFaultGraphQLQuery query, GetChaosFaultProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_CHAOS_FAULT, new TypeRef<FaultDetails>(){});
    }

    public List<Chart> listChaosFaults(ListChaosFaultsGraphQLQuery query, ListChaosFaultsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_CHAOS_FAULTS, new TypeRef<List<Chart>>(){});
    }

    // others
    public ServerVersionResponse getServerVersion(GetServerVersionGraphQLQuery query, GetServerVersionProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_SERVER_VERSION, new TypeRef<ServerVersionResponse>(){});
    }

    public InfraVersionDetails getVersionDetails(GetVersionDetailsGraphQLQuery query, GetVersionDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_VERSION_DETAILS, new TypeRef<InfraVersionDetails>(){});
    }

    public SSHKey generateSSHKey(GenerateSSHKeyGraphQLQuery query, GenerateSSHKeyProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GENERATE_SSH_KEY, new TypeRef<SSHKey>(){});
    }

    public GetManifestWithInfraIDResponse getManifestWithInfraID(GetManifestWithInfraIDGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_MANIFEST_WITH_INFRA_ID, new TypeRef<GetManifestWithInfraIDResponse>(){});
    }

    // subscription is not supported in current version

//    public InfraEventResponse getInfraEvents(GetInfraEventsGraphQLQuery query, GetInfraEventsProjectionRoot projectionRoot){
//        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
//        GraphQLResponse response = graphQLClient.query(request);
//        return response.extractValueAsObject("getInfraEvents", new TypeRef<InfraEventResponse>(){});
//    }
//
//    public KubeNamespaceResponse getKubeNamespace(GetKubeNamespaceGraphQLQuery query, GetKubeNamespaceProjectionRoot projectionRoot){
//        String request = new GraphQLQueryRequest(query,projectionRoot).serialize();
//        GraphQLResponse response = graphQLClient.query(request);
//        return response.extractValueAsObject("getKubeNamespace", new TypeRef<KubeNamespaceResponse>(){});
//    }
//
//    public KubeObjectResponse getKubeObject(GetKubeObjectGraphQLQuery query, GetKubeObjectProjectionRoot projectionRoot){
//        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
//        GraphQLResponse response = graphQLClient.query(request);
//        return response.extractValueAsObject("getKubeObject", new TypeRef<KubeObjectResponse>(){});
//    }
//
//    public PodLogResponse getPodLog(GetPodLogGraphQLQuery query, GetPodLogProjectionRoot projectionRoot){
//        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
//        GraphQLResponse response = graphQLClient.query(request);
//        return response.extractValueAsObject("getPodLog", new TypeRef<PodLogResponse>(){});
//    }
//
//    public InfraActionResponse infraConnect(InfraConnectGraphQLQuery query, InfraConnectProjectionRoot projectionRoot){
//        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
//        GraphQLResponse response = graphQLClient.query(request);
//        return response.extractValueAsObject("infraConnect", new TypeRef<InfraActionResponse>(){});
//    }

    private String sanitizeURL(String url) {
        // TODO: need to add a validate URL without protocol
        // edge case: If you're calling a service from within Kubernetes, you don't need a protocol.
        return url.replaceAll("/$", "");
    }
}