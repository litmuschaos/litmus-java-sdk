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
 * , <a href="https://github.com/litmuschaos/litmus-java-sdk/blob/master/src/test/java/LitmusClientTest.java">Example</a>
 */
public class LitmusClient implements AutoCloseable {

    private final String token;
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
     * @param userId userId
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
     * @param request request
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
     * @param request request
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
     * @param userId userId
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
     * @param request request
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
     * @param request request
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
     * @param request request
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
     * @param request request
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
     * @param request request
     * @return return the response of the update user state request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse updateUserState(UserStateUpdateRequest request) throws IOException, LitmusApiException {
        return httpClient.post(UPDATE_USER_STATE, token, request, CommonResponse.class);
    }

    /**
     * Get capabilities of Auth Server.
     *
     * @return Returns the capabilities of the Auth Server.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return httpClient.get(CAPABILITIES, CapabilityResponse.class);
    }

    /**
     * Get projects by user ID.
     *
     * @param request request
     * @return Returns the list of projects.
     * @throws IOException
     * @throws LitmusApiException
     */
    public ListProjectsResponse listProjects(ListProjectRequest request)
            throws IOException, LitmusApiException {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put(PAGE, String.valueOf(request.getPage()));
        requestParam.put(LIMIT, String.valueOf(request.getLimit()));
        requestParam.put(SORT_FIELD, request.getSortField());
        requestParam.put(CREATED_BY_ME, String.valueOf(request.getCreatedByMe()));
        return httpClient.get(LIST_PROJECTS, token, requestParam, ListProjectsResponse.class);
    }

    /**
     * Create a new project.
     *
     * @param request request
     * @return Returns the created project.
     * @throws IOException
     * @throws LitmusApiException
     */
    public ProjectResponse createProject(CreateProjectRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post(CREATE_PROJECT, token, request, ProjectResponse.class);
    }

    /**
     * Update the project name.
     *
     * @param request request
     * @return Returns the projectID with message.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse updateProjectName(ProjectNameRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post(UPDATE_PROJECT_NAME, token, request, CommonResponse.class);
    }

    /**
     * Get the project details.
     *
     * @param projectId projectId
     * @return Returns the project details.
     * @throws IOException
     * @throws LitmusApiException
     */
    public ProjectResponse getProject(String projectId) throws IOException, LitmusApiException {
        return httpClient.get(GET_PROJECT + "/" + projectId, token, ProjectResponse.class);
    }

    /**
     * Get the owner projects.
     *
     * @return Returns the list of owner projects.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<ProjectResponse> getOwnerProjects() throws IOException, LitmusApiException {
        TypeToken<List<ProjectResponse>> typeToken = new TypeToken<List<ProjectResponse>>() {};
        return httpClient.get(GET_OWNER_PROJECTS, token, typeToken);
    }

    /**
     * Leave the project.
     *
     * @param request request
     * @return return the response of the leave project request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse leaveProject(LeaveProjectRequest request)
            throws IOException, LitmusApiException {
        return httpClient.post(LEAVE_PROJECT, token, request, CommonResponse.class);
    }

    /**
     * Get the project role.
     *
     * @param projectId projectId
     * @return Returns the project role.
     * @throws IOException
     * @throws LitmusApiException
     */
    public ProjectRoleResponse getProjectRole(String projectId)
            throws IOException, LitmusApiException {
        return httpClient.get(GET_PROJECT_ROLE + "/" + projectId, token, ProjectRoleResponse.class);
    }

    /**
     * Get the user with project.
     *
     * @param username username
     * @return Returns the user with project.
     * @throws IOException
     * @throws LitmusApiException
     */
    public UserWithProjectResponse getUserWithProject(String username)
            throws IOException, LitmusApiException {
        return httpClient.get(GET_USER_WITH_PROJECT + "/" + username, token,
                UserWithProjectResponse.class);
    }

    /**
     * Get the project stats.
     *
     * @return Returns the project stats.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<ProjectsStatsResponse> getProjectsStats() throws IOException, LitmusApiException {
        TypeToken<List<ProjectsStatsResponse>> typeToken = new TypeToken<List<ProjectsStatsResponse>>() {};
        return httpClient.get(GET_PROJECTS_STATS, token, typeToken);
    }

    /**
     * Get the project members.
     *
     * @param projectID projectID
     * @param status status
     * @return Returns the list of project members.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<ProjectMemberResponse> getProjectMembers(String projectID, String status)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get(GET_PROJECT_MEMBERS + "/" + projectID + "/" + status, token, typeToken);
    }

    /**
     * Get the project owners.
     *
     * @param projectID projectID
     * @return Returns the list of project owners.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<ProjectMemberResponse> getProjectOwners(String projectID)
            throws IOException, LitmusApiException {
        TypeToken<List<ProjectMemberResponse>> typeToken = new TypeToken<List<ProjectMemberResponse>>() {};
        return httpClient.get(GET_PROJECT_OWNERS + "/" + projectID, token, typeToken);
    }

    /**
     * Delete the project.
     *
     * @param projectID projectID
     * @return return the response of the delete project request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse deleteProject(String projectID) throws IOException, LitmusApiException {
        return httpClient.post(DELETE_PROJECT + "/" + projectID, token, CommonResponse.class);
    }

    /**
     * Get the project invitation.
     *
     * @param request request
     * @return Returns the list of project invitations.
     * @throws IOException
     * @throws LitmusApiException
     */
    public SendInvitationResponse sendInvitation(SendInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(SEND_INVITATION, token, request, SendInvitationResponse.class);
    }

    /**
     * Accept the project invitation.
     *
     * @param request request
     * @return return the response of the accept invitation request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse acceptInvitation(AcceptInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(ACCEPT_INVITATION, token, request, CommonResponse.class);
    }

    /**
     * Decline the project invitation.
     *
     * @param request request
     * @return return the response of the decline invitation request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse declineInvitation(DeclineInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(DECLINE_INVITATION, token, request, CommonResponse.class);
    }

    /**
     * Remove the project invitation.
     *
     * @param request request
     * @return return the response of the remove invitation request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse removeInvitation(RemoveInvitationRequest request) throws IOException, LitmusApiException {
        return httpClient.post(REMOVE_INVITATION, token, request, CommonResponse.class);
    }

    /**
     * Get the project invitation status.
     *
     * @param status status
     * @return Returns the list of project invitations.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<ListInvitationResponse> listInvitation(String status)
            throws IOException, LitmusApiException {
        TypeToken<List<ListInvitationResponse>> typeToken = new TypeToken<List<ListInvitationResponse>>() {};
        return httpClient.get(LIST_INVITATIONS_WITH_FILTERS + "/" + status, token, typeToken);
    }

    /**
     * Invite users.
     *
     * @param projectId project id
     * @return Returns the list of invited users.
     * @throws IOException
     * @throws LitmusApiException
     */
    public List<InviteUsersResponse> inviteUsers(String projectId)
            throws IOException, LitmusApiException {
        TypeToken<List<InviteUsersResponse>> typeToken = new TypeToken<List<InviteUsersResponse>>() {};
        return httpClient.get(INVITE_USERS + "/" + projectId, token, typeToken);
    }

    /**
     * Update member role.
     *
     * @param request request
     * @return return the response of the update member role request.
     * @throws IOException
     * @throws LitmusApiException
     */
    public CommonResponse updateMemberRole(UpdateMemberRoleRequest request) throws IOException, LitmusApiException{
        return httpClient.post(UPDATE_MEMBER_ROLE, token, request, CommonResponse.class);
    }

    /**
     * status of the LitmusChaos AUTH API.
     *
     * @return Returns the status of the LitmusChaos AUTH API.
     * @throws IOException
     * @throws LitmusApiException
     */
    public StatusResponse status() throws IOException, LitmusApiException {
        return httpClient.get(STATUS, token, StatusResponse.class);
    }

    /**
     * readiness of the LitmusChaos AUTH API.
     *
     * @return Returns the readiness of the LitmusChaos AUTH API.
     * @throws IOException
     * @throws LitmusApiException
     */
    public ReadinessResponse readiness() throws IOException, LitmusApiException {
        return httpClient.get(READINESS, token, ReadinessResponse.class);
    }

    /**
     * Get the environment.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the environment.
     */
    public Environment getEnvironment(GetEnvironmentGraphQLQuery query, GetEnvironmentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_ENVIRONMENT, new TypeRef<Environment>(){});
    }

    /**
     * List the environments.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of environments.
     */
    public ListEnvironmentResponse listEnvironments(ListEnvironmentsGraphQLQuery query, ListEnvironmentsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_ENVIRONMENTS, new TypeRef<ListEnvironmentResponse>(){});
    }

    /**
     * Create the environment.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the created environment.
     */
    public Environment createEnvironment(CreateEnvironmentGraphQLQuery query, CreateEnvironmentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CREATE_ENVIRONMENT, new TypeRef<Environment>(){});
    }

    /**
     * Delete the environment.
     *
     * @param query
     * @return Returns the response of the delete environment request.
     */
    public DeleteEnvironmentResponse deleteEnvironment(DeleteEnvironmentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_ENVIRONMENT, new TypeRef<DeleteEnvironmentResponse>(){});
    }

    /**
     * Update the environment.
     *
     * @param query
     * @return Returns the updated environment.
     */
    public UpdateEnvironmentResponse updateEnvironment(UpdateEnvironmentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_ENVIRONMENT, new TypeRef<UpdateEnvironmentResponse>(){});
    }

    /**
     * Get the infra.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the infra.
     */
    public Infra getInfra(GetInfraGraphQLQuery query, GetInfraProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA, new TypeRef<Infra>(){});
    }

    /**
     * List the infras.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of infras.
     */
    public ListInfraResponse listInfras(ListInfrasGraphQLQuery query, ListInfrasProjectionRoot projectionRoot) {
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_INFRAS, new TypeRef<ListInfraResponse>() {});
    }

    /**
     * Get the infra details.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the infra details.
     */
    public Infra getInfraDetails(GetInfraDetailsGraphQLQuery query, GetInfraDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA_DETAILS, new TypeRef<Infra>(){});
    }

    /**
     * Get the infra stats.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the infra stats.
     */
    public GetInfraStatsResponse getInfraStats(GetInfraStatsGraphQLQuery query, GetInfraStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA_STATS, new TypeRef<GetInfraStatsResponse>(){});
    }

    /**
     * Get the infra manifest.
     *
     * @param query
     * @return Returns the infra manifest.
     */
    public GetInfraManifestResponse getInfraManifest(GetInfraManifestGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_INFRA_MANIFEST, new TypeRef<GetInfraManifestResponse>(){});
    }

    /**
     * Confirm the infra registration.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the response of the confirm infra registration request.
     */
    public ConfirmInfraRegistrationResponse confirmInfraRegistration(ConfirmInfraRegistrationGraphQLQuery query, ConfirmInfraRegistrationProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CONFIRM_INFRA_REGISTRATION, new TypeRef<ConfirmInfraRegistrationResponse>(){});
    }

    /**
     * Delete the infra.
     *
     * @param query
     * @return Returns the response of the delete infra request.
     */
    public DeleteInfraResponse deleteInfra(DeleteInfraGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_INFRA, new TypeRef<DeleteInfraResponse>(){});
    }

    /**
     * Register the infra.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the response of the register infra request.
     */
    public RegisterInfraResponse registerInfra(RegisterInfraGraphQLQuery query, RegisterInfraProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(REGISTER_INFRA, new TypeRef<RegisterInfraResponse>(){});
    }

    /**
     * Get the list of chaos hubs.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of chaos hubs.
     */
    public List<ChaosHubStatus> listChaosHub(ListChaosHubGraphQLQuery query, ListChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_CHAOS_HUB, new TypeRef<List<ChaosHubStatus>>(){});
    }

    /**
     * Get the chaos hub.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the chaos hub.
     */
    public ChaosHubStatus getChaosHub(GetChaosHubGraphQLQuery query, GetChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_CHAOS_HUB, new TypeRef<ChaosHubStatus>() {});
    }

    /**
     * Get the chaos hub stats.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the chaos hub stats.
     */
    public GetChaosHubStatsResponse getChaosHubStats(GetChaosHubStatsGraphQLQuery query, GetChaosHubStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_CHAOS_HUB_STATS, new TypeRef<GetChaosHubStatsResponse>() {});
    }

    /**
     * Add the chaos hub.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the added chaos hub.
     */
    public ChaosHub addChaosHub(AddChaosHubGraphQLQuery query, AddChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ADD_CHAOS_HUB, new TypeRef<ChaosHub>(){});
    }

    /**
     * Add the remote chaos hub.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the added remote chaos hub.
     */
    public ChaosHub addRemoteChaosHub(AddRemoteChaosHubGraphQLQuery query, AddRemoteChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ADD_REMOTE_CHAOS_HUB, new TypeRef<ChaosHub>() {});
    }

    /**
     * Delete the chaos hub.
     *
     * @param query
     * @return Returns the response of the delete chaos hub request.
     */
    public DeleteChaosHubResponse deleteChaosHub(DeleteChaosHubGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_CHAOS_HUB, new TypeRef<DeleteChaosHubResponse>(){});
    }

    /**
     * Save the chaos hub.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the saved chaos hub.
     */
    public ChaosHub saveChaosHub(SaveChaosHubGraphQLQuery query, SaveChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(SAVE_CHAOS_HUB, new TypeRef<ChaosHub>() {});
    }

    /**
     * Sync the chaos hub.
     *
     * @param query
     * @return Returns the response of the sync chaos hub request.
     */
    public SyncChaosHubResponse syncChaosHub(SyncChaosHubGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(SYNC_CHAOS_HUB, new TypeRef<SyncChaosHubResponse>() {});
    }

    /**
     * Update the chaos hub.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the updated chaos hub.
     */
    public ChaosHub updateChaosHub(UpdateChaosHubGraphQLQuery query, UpdateChaosHubProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_CHAOS_HUB, new TypeRef<ChaosHub>() {});
    }

    /**
     * Get the chaos experiment.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the chaos experiment.
     */
    public GetExperimentResponse getExperiment(GetExperimentGraphQLQuery query, GetExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT, new TypeRef<GetExperimentResponse>(){});
    }

    /**
     * List the chaos experiments.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of chaos experiments.
     */
    public ListExperimentResponse listExperiment(ListExperimentGraphQLQuery query, ListExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_EXPERIMENT, new TypeRef<ListExperimentResponse>(){});
    }

    /**
     * Get the chaos experiment stats.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the chaos experiment stats.
     */
    public GetExperimentStatsResponse getExperimentStats(GetExperimentStatsGraphQLQuery query, GetExperimentStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT_STATS, new TypeRef<GetExperimentStatsResponse>(){});
    }

    /**
     * Get the predefined chaos experiment.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the predefined chaos experiment.
     */
    public List<PredefinedExperimentList> getPredefinedExperiment(GetPredefinedExperimentGraphQLQuery query, GetPredefinedExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PREDEFINED_EXPERIMENT, new TypeRef<List<PredefinedExperimentList>>(){});
    }

    /**
     * List the predefined chaos experiments.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of predefined chaos experiments.
     */
    public List<PredefinedExperimentList> listPredefinedExperiments(ListPredefinedExperimentsGraphQLQuery query, ListPredefinedExperimentsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_PREDEFINED_EXPERIMENTS, new TypeRef<List<PredefinedExperimentList>>(){});
    }

    /**
     * Run the chaos experiment.
     * @param query
     * @param projectionRoot
     * @return Returns the response of the run chaos experiment request.
     */
    public RunChaosExperimentResponse runChaosExperiment(RunChaosExperimentGraphQLQuery query, RunChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(RUN_CHAOS_EXPERIMENT, new TypeRef<RunChaosExperimentResponse>(){});
    }

    /**
     * Save the chaos experiment.
     *
     * @param query
     * @return Returns the response of the save chaos experiment request.
     */
    public SaveChaosExperimentResponse saveChaosExperiment(SaveChaosExperimentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(SAVE_CHAOS_EXPERIMENT, new TypeRef<SaveChaosExperimentResponse>(){});
    }

    /**
     * Update the chaos experiment.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the updated chaos experiment.
     */
    public ChaosExperimentResponse updateChaosExperiment(UpdateChaosExperimentGraphQLQuery query, UpdateChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_CHAOS_EXPERIMENT, new TypeRef<ChaosExperimentResponse>(){});
    }

    /**
     * Create the chaos experiment.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the created chaos experiment.
     */
    public ChaosExperimentResponse createChaosExperiment(CreateChaosExperimentGraphQLQuery query, CreateChaosExperimentProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CREATE_CHAOS_EXPERIMENT, new TypeRef<ChaosExperimentResponse>(){});
    }

    /**
     * Delete the chaos experiment.
     *
     * @param query
     * @return Returns the response of the delete chaos experiment request.
     */
    public DeleteChaosExperimentResponse deleteChaosExperiment(DeleteChaosExperimentGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_CHAOS_EXPERIMENT, new TypeRef<DeleteChaosExperimentResponse>(){});
    }

    /**
     * Update the cron chaos experiment state.
     * @param query
     * @return Returns the response of the update cron chaos experiment state request.
     */
    public UpdateCronExperimentStateResponse updateCronExperimentState(UpdateCronExperimentStateGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_CRON_EXPERIMENT_STATE, new TypeRef<UpdateCronExperimentStateResponse>(){});
    }

    /**
     * Get the chaos experiment run.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the chaos experiment run.
     */
    public ExperimentRun getExperimentRun(GetExperimentRunGraphQLQuery query, GetExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT_RUN, new TypeRef<ExperimentRun>(){});
    }

    /**
     * Get the chaos experiment run stats.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the chaos experiment run stats.
     */
    public GetExperimentRunStatsResponse getExperimentRunStats(GetExperimentRunStatsGraphQLQuery query, GetExperimentRunStatsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_EXPERIMENT_RUN_STATS, new TypeRef<GetExperimentRunStatsResponse>(){});
    }

    /**
     * List the chaos experiment runs.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of chaos experiment runs.
     */
    public ListExperimentRunResponse listExperimentRun(ListExperimentRunGraphQLQuery query, ListExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_EXPERIMENT_RUN, new TypeRef<ListExperimentRunResponse>(){});
    }

    /**
     * Creates a new experiment run and sends it to subscriber
     *
     * @param query
     * @return Returns the response of the chaos experiment run request.
     */
    public ChaosExperimentRunResponse chaosExperimentRun(ChaosExperimentRunGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CHAOS_EXPERIMENT_RUN, new TypeRef<ChaosExperimentRunResponse>(){});
    }

    /**
     * Stop the chaos experiment run.
     *
     * @param query
     * @return Returns the response of the stop chaos experiment run request.
     */
    public StopExperimentRunsResponse stopExperimentRuns(StopExperimentRunsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(STOP_EXPERIMENT_RUNS, new TypeRef<StopExperimentRunsResponse>(){});
    }

    /**
     * Returns the git configuration for gitops
     *
     * @param query
     * @param projectionRoot
     * @return Returns the git configuration for gitops
     */
    public GitConfigResponse getGitOpsDetails(GetGitOpsDetailsGraphQLQuery query, GetGitOpsDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_GIT_OPS_DETAILS, new TypeRef<GitConfigResponse>(){});
    }

    /**
     * Disables gitops settings in the project
     *
     * @param query
     * @return Returns the response of the disable gitops request.
     */
    public DisableGitOpsResponse disableGitOps(DisableGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DISABLE_GIT_OPS, new TypeRef<DisableGitOpsResponse>(){});
    }

    /**
     * Enables gitops settings in the project
     *
     * @param query
     * @return Returns the response of the enable gitops request.
     */
    public EnableGitOpsResponse enableGitOps(EnableGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ENABLE_GIT_OPS, new TypeRef<EnableGitOpsResponse>(){});
    }

    /**
     * Sends workflow run request(single run workflow only) to agent on gitops notification
     *
     * @param query
     * @return Returns the response of the gitops notifier request.
     */
    public GitOpsNotifierResponse gitopsNotifier(GitopsNotifierGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GITOPS_NOTIFIER, new TypeRef<GitOpsNotifierResponse>(){});
    }

    /**
     * Updates the gitops settings in the project
     *
     * @param query
     * @return Returns the response of the update gitops request.
     */
    public UpdateGitOpsResponse updateGitOps(UpdateGitOpsGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_GIT_OPS, new TypeRef<UpdateGitOpsResponse>(){});
    }

    /**
     * Get the image registry.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the image registry.
     */
    public ImageRegistryResponse getImageRegistry(GetImageRegistryGraphQLQuery query, GetImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_IMAGE_REGISTRY, new TypeRef<ImageRegistryResponse>(){});
    }

    /**
     * List the image registries.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of image registries.
     */
    public List<ImageRegistryResponse> listImageRegistry(ListImageRegistryGraphQLQuery query, ListImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_IMAGE_REGISTRY, new TypeRef<List<ImageRegistryResponse>>(){});
    }

    /**
     * Create the image registry.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the created image registry.
     */
    public ImageRegistryResponse createImageRegistry(CreateImageRegistryGraphQLQuery query, CreateImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(CREATE_IMAGE_REGISTRY, new TypeRef<ImageRegistryResponse>(){});
    }

    /**
     * Delete the image registry.
     *
     * @param query
     * @return Returns the response of the delete image registry request.
     */
    public DeleteImageRegistryResponse deleteImageRegistry(DeleteImageRegistryGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_IMAGE_REGISTRY, new TypeRef<DeleteImageRegistryResponse>(){});
    }

    /**
     * Update the image registry.
     *
     * @param query
     * @param projectionRoot
     * @return Returns the updated image registry.
     */
    public ImageRegistryResponse updateImageRegistry(UpdateImageRegistryGraphQLQuery query, UpdateImageRegistryProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_IMAGE_REGISTRY, new TypeRef<ImageRegistryResponse>(){});
    }

    /**
     * Returns the list of Probes based on various filter parameters
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of Probes
     */
    public List<Probe> listProbes(ListProbesGraphQLQuery query, ListProbesProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_PROBES, new TypeRef<List<Probe>>(){});
    }

    /**
     * Returns the Probe based on the given probeID
     *
     * @param query
     * @param projectionRoot
     * @return Returns the Probe
     */
    public Probe getProbe(GetProbeGraphQLQuery query, GetProbeProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBE, new TypeRef<Probe>(){});
    }

    /**
     * Validates if a probe is already present, returns true if unique
     *
     * @param query
     * @return Returns the response of the validate unique probe request
     */
    public ValidateUniqueProbeResponse validateUniqueProbe(ValidateUniqueProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(VALIDATE_UNIQUE_PROBE, new TypeRef<ValidateUniqueProbeResponse>(){});
    }

    /**
     * Defines the response of the Probe reference API
     *
     * @param query
     * @param projectionRoot
     * @return Returns the response of the get probe reference request
     */
    public GetProbeReferenceResponse getProbeReference(GetProbeReferenceGraphQLQuery query, GetProbeReferenceProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBE_REFERENCE, new TypeRef<GetProbeReferenceResponse>(){});
    }

    /**
     * Returns the YAML of the Probe based on the given probeID
     *
     * @param query
     * @return Returns the YAML of the Probe
     */
    public GetProbeYAMLResponse getProbeYAML(GetProbeYAMLGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBE_YAML, new TypeRef<GetProbeYAMLResponse>(){});
    }

    /**
     * Returns the list of Probes based on the given experimentRunID
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of Probes
     */
    public List<GetProbesInExperimentRunResponse> getProbesInExperimentRun(GetProbesInExperimentRunGraphQLQuery query, GetProbesInExperimentRunProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_PROBES_IN_EXPERIMENT_RUN, new TypeRef<List<GetProbesInExperimentRunResponse>>(){});
    }

    /**
     * Adds a new Probe
     *
     * @param query
     * @param projectionRoot
     * @return Returns the added Probe
     */
    public Probe addProbe(AddProbeGraphQLQuery query, AddProbeProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(ADD_PROBE, new TypeRef<Probe>(){});
    }

    /**
     * Deletes the Probe based on the given probeID
     *
     * @param query
     * @return Returns the response of the delete probe request
     */
    public DeleteProbeResponse deleteProbe(DeleteProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(DELETE_PROBE, new TypeRef<DeleteProbeResponse>(){});
    }

    /**
     * Updates the Probe based on the given probeID
     *
     * @param query
     * @return Returns the updated Probe
     */
    public UpdateProbeResponse updateProbe(UpdateProbeGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(UPDATE_PROBE, new TypeRef<UpdateProbeResponse>(){});
    }

    /**
     * Get the fault list from a ChaosHub
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of faults
     */
    public FaultDetails getChaosFault(GetChaosFaultGraphQLQuery query, GetChaosFaultProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_CHAOS_FAULT, new TypeRef<FaultDetails>(){});
    }

    /**
     * List the chaos faults
     *
     * @param query
     * @param projectionRoot
     * @return Returns the list of chaos faults
     */
    public List<Chart> listChaosFaults(ListChaosFaultsGraphQLQuery query, ListChaosFaultsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(LIST_CHAOS_FAULTS, new TypeRef<List<Chart>>(){});
    }

    /**
     * Returns version of gql server
     *
     * @param query
     * @param projectionRoot
     * @return Returns the version of gql server
     */
    public ServerVersionResponse getServerVersion(GetServerVersionGraphQLQuery query, GetServerVersionProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_SERVER_VERSION, new TypeRef<ServerVersionResponse>(){});
    }

    /**
     * Query to get the latest version of infra available
     *
     * @param query
     * @param projectionRoot
     * @return Returns the latest version of infra available
     */
    public InfraVersionDetails getVersionDetails(GetVersionDetailsGraphQLQuery query, GetVersionDetailsProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_VERSION_DETAILS, new TypeRef<InfraVersionDetails>(){});
    }

    /**
     * Generates Private and Public key for SSH authentication
     *
     * @param query
     * @param projectionRoot
     * @return Returns the generated SSH key
     */
    public SSHKey generateSSHKey(GenerateSSHKeyGraphQLQuery query, GenerateSSHKeyProjectionRoot projectionRoot){
        String request = new GraphQLQueryRequest(query, projectionRoot).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GENERATE_SSH_KEY, new TypeRef<SSHKey>(){});
    }

    /**
     * Fetches manifest details
     *
     * @param query
     * @return Returns the manifest details
     */
    public GetManifestWithInfraIDResponse getManifestWithInfraID(GetManifestWithInfraIDGraphQLQuery query){
        String request = new GraphQLQueryRequest(query).serialize();
        GraphQLResponse response = graphQLClient.query(request);
        return response.extractValueAsObject(GET_MANIFEST_WITH_INFRA_ID, new TypeRef<GetManifestWithInfraIDResponse>(){});
    }

    // TODO: subscription is not supported in current version

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