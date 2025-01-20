import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.client.*;
import io.litmuschaos.generated.types.*;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MockedLitmusClient {

    public MockedLitmusClient(String host, String token) {
    }

    public ListTokensResponse getTokens(String userId) throws IOException, LitmusApiException {
        List<TokenResponse> tokens = new ArrayList<>();
        tokens.add(new TokenResponse("litmus", "litmus", "litmus token", System.currentTimeMillis(), System.currentTimeMillis()));
        return new ListTokensResponse(tokens);
    }

    public TokenCreateResponse createToken(TokenCreateRequest request) throws IOException, LitmusApiException {
        return new TokenCreateResponse("litmus token", "Bearer");

    }

    public CommonResponse deleteToken(TokenDeleteRequest request) throws IOException, LitmusApiException {
        return new CommonResponse("Token deleted successfully");
    }

    public UserResponse getUser(String userId) throws IOException, LitmusApiException {
        return new UserResponse.Builder()
                .updatedAt(System.currentTimeMillis())
                .createdAt(System.currentTimeMillis())
                .createdBy(new UserResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"))
                .updatedBy(new UserResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"))
                .isRemoved(false)
                .userID(userId)
                .username("mockUsername")
                .salt("mockSalt")
                .email("mockEmail@example.com")
                .name("Mock Name")
                .role("Mock Role")
                .isInitialLogin(false)
                .build();
    }

    public List<UserResponse> getUsers() throws IOException, LitmusApiException {
        List<UserResponse> users = new ArrayList<>();
        users.add(new UserResponse.Builder()
                .updatedAt(System.currentTimeMillis())
                .createdAt(System.currentTimeMillis() - 100000)
                .createdBy(new UserResponse.CreatedBy("creatorId1", "creatorUsername1", "creator1@example.com"))
                .updatedBy(new UserResponse.UpdatedBy("updaterId1", "updaterUsername1", "updater1@example.com"))
                .isRemoved(false)
                .userID("userId1")
                .username("mockUsername1")
                .salt("mockSalt1")
                .email("mockEmail1@example.com")
                .name("Mock Name 1")
                .role("Mock Role 1")
                .isInitialLogin(false)
                .build());
        users.add(new UserResponse.Builder()
                .updatedAt(System.currentTimeMillis())
                .createdAt(System.currentTimeMillis() - 200000)
                .createdBy(new UserResponse.CreatedBy("creatorId2", "creatorUsername2", "creator2@example.com"))
                .updatedBy(new UserResponse.UpdatedBy("updaterId2", "updaterUsername2", "updater2@example.com"))
                .isRemoved(false)
                .userID("userId2")
                .username("mockUsername2")
                .salt("mockSalt2")
                .email("mockEmail2@example.com")
                .name("Mock Name 2")
                .role("Mock Role 2")
                .isInitialLogin(false)
                .build());
        return users;
    }

    public PasswordUpdateResponse updatePassword(PasswordUpdateRequest request) throws IOException, LitmusApiException {
        return new PasswordUpdateResponse("Password updated successfully", "mockProjectId");
    }

    public UserResponse createUser(UserCreateRequest request) throws IOException, LitmusApiException {
        return new UserResponse.Builder()
                .updatedAt(System.currentTimeMillis())
                .createdAt(System.currentTimeMillis())
                .createdBy(new UserResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"))
                .updatedBy(new UserResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"))
                .isRemoved(false)
                .userID("mockUserId")
                .username(request.getUsername())
                .salt("mockSalt")
                .email(request.getEmail())
                .name(request.getName())
                .role(request.getRole())
                .isInitialLogin(true)
                .build();
    }

    public CommonResponse resetPassword(PasswordResetRequest request) throws IOException, LitmusApiException {
        return new CommonResponse("Password reset successfully");
    }

    public CommonResponse updateUserDetails(UserDetailsUpdateRequest request) throws IOException, LitmusApiException {
        return new CommonResponse("User details updated successfully");
    }

    public CommonResponse updateUserState(UserStateUpdateRequest request) throws IOException, LitmusApiException {
        return new CommonResponse("User state updated successfully");
    }

    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return new CapabilityResponse();
    }

    public ListProjectsResponse listProjects(ListProjectRequest request) throws IOException, LitmusApiException {
        List<ProjectResponse> projects = new ArrayList<>();
        projects.add(new ProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                new ProjectResponse.CreatedBy("creatorId1", "creatorUsername1", "mock"),
                new ProjectResponse.UpdatedBy("updaterId1", "updaterUsername1", "mock"),
                false,
                "projectId1",
                "mockProjectName1",
                new ArrayList<>(),
                "mockOwner1",
                new ArrayList<>(),
                "mockDescription1"
        ));
        projects.add(new ProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis() - 200000,
                new ProjectResponse.CreatedBy("creatorId2", "creatorUsername2", "creator2@example.com"),
                new ProjectResponse.UpdatedBy("updaterId2", "updaterUsername2", "updater2@example.com"),
                false,
                "projectId2",
                "mockProjectName1",
                new ArrayList<>(),
                "mockOwner1",
                new ArrayList<>(),
                "mockDescription1"
        ));
        return new ListProjectsResponse(projects, 2);
    }

    public ProjectResponse createProject(CreateProjectRequest request) throws IOException, LitmusApiException {
        return new ProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis() - 100000,
                new ProjectResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"),
                new ProjectResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"),
                false,
                "projectId",
                "litmus",
                new ArrayList<>(),
                "mockState",
                new ArrayList<>(),
                "mockDescription"
        );
    }

    public CommonResponse updateProjectName(ProjectNameRequest request) throws IOException, LitmusApiException {
        return new CommonResponse("Project name updated successfully");
    }

    public ProjectResponse getProject(String projectId) throws IOException, LitmusApiException {
        return new ProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis() - 100000,
                new ProjectResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"),
                new ProjectResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"),
                false,
                "1234",
                "litmus",
                new ArrayList<>(),
                "mockState",
                new ArrayList<>(),
                "mockDescription"
        );
    }

    public List<ProjectResponse> getOwnerProjects() throws IOException, LitmusApiException {
        List<ProjectResponse> projects = new ArrayList<>();
        projects.add(
                new ProjectResponse(
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 100000,
                        new ProjectResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"),
                        new ProjectResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"),
                        false,
                        "projectId",
                        "mockProjectName",
                        new ArrayList<>(),
                        "mockState",
                        new ArrayList<>(),
                        "mockDescription"
                )
        );
        projects.add(
                new ProjectResponse(
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 100000,
                        new ProjectResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"),
                        new ProjectResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"),
                        false,
                        "projectId",
                        "mockProjectName",
                        new ArrayList<>(),
                        "mockState",
                        new ArrayList<>(),
                        "mockDescription"
                )
        );
        return projects;
    }

    public CommonResponse leaveProject(LeaveProjectRequest request) throws IOException, LitmusApiException {
        return new CommonResponse("User left project successfully");
    }

    public ProjectRoleResponse getProjectRole(String projectId) throws IOException, LitmusApiException {
        return new ProjectRoleResponse("Viewer");
    }

    public UserWithProjectResponse getUserWithProject(String username) throws IOException, LitmusApiException {
        List<ProjectResponse> projects = new ArrayList<>();
        projects.add(new ProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis() - 100000,
                new ProjectResponse.CreatedBy("creatorId1", "creatorUsername1", "creator1@example.com"),
                new ProjectResponse.UpdatedBy("updaterId1", "updaterUsername1", "updater1@example.com"),
                false,
                "projectId1",
                "mockProjectName1",
                new ArrayList<>(),
                "mockOwner1",
                new ArrayList<>(),
                "mockDescription1"
        ));
        projects.add(new ProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis() - 200000,
                new ProjectResponse.CreatedBy("creatorId2", "creatorUsername2", "creator2@example.com"),
                new ProjectResponse.UpdatedBy("updaterId2", "updaterUsername2", "updater2@example.com"),
                false,
                "projectId2",
                "mockProjectName2",
                new ArrayList<>(),
                "mockOwner2",
                new ArrayList<>(),
                "mockDescription2"
        ));
        return new UserWithProjectResponse(
                System.currentTimeMillis(),
                System.currentTimeMillis() - 100000,
                new ProjectResponse.CreatedBy("creatorId", "creatorUsername", "creator@example.com"),
                new ProjectResponse.UpdatedBy("updaterId", "updaterUsername", "updater@example.com"),
                false,
                "mockUserId",
                username,
                "mockEmail@example.com",
                "Mock Name",
                projects
        );
    }

    public List<ProjectsStatsResponse> getProjectsStats() throws IOException, LitmusApiException {
        List<ProjectsStatsResponse> stats = new ArrayList<>();
        stats.add(
                new ProjectsStatsResponse(
                        "mockProjectName1",
                        "projectId1",
                        new ProjectsStatsResponse.Members(
                                List.of(new ProjectsStatsResponse.Owner("ownerId1", "ownerUsername1", "mockInvitation1", System.currentTimeMillis(), System.currentTimeMillis() - 100000)),
                                10
                        )
                )
        );
        stats.add(
                new ProjectsStatsResponse(
                        "mockProjectName1",
                        "projectId1",
                        new ProjectsStatsResponse.Members(
                                List.of(new ProjectsStatsResponse.Owner("ownerId1", "ownerUsername1", "mockInvitation1", System.currentTimeMillis(), System.currentTimeMillis() - 100000)),
                                10
                        )
                )
        );
        return stats;
    }

    public List<ProjectMemberResponse> getProjectMembers(String projectID, String status) throws IOException, LitmusApiException {
        List<ProjectMemberResponse> members = new ArrayList<>();
        members.add(
                new ProjectMemberResponse(
                        "userId1",
                        "mockUsername1",
                        "mockEmail1@example.com",
                        "Mock Name 1",
                        "Mock Role 1",
                        "mockInvitation",
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 100000
                )
        );
        members.add(
                new ProjectMemberResponse(
                        "userId1",
                        "mockUsername1",
                        "mockEmail1@example.com",
                        "Mock Name 1",
                        "Mock Role 1",
                        "mockInvitation",
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 100000
                )
        );
        return members;
    }

    public List<ProjectMemberResponse> getProjectOwners(String projectID) throws IOException, LitmusApiException {
        List<ProjectMemberResponse> owners = new ArrayList<>();
        owners.add(
                new ProjectMemberResponse(
                        "userId1",
                        "mockUsername1",
                        "mockEmail1@example.com",
                        "Mock Name 1",
                        "Mock Role 1",
                        "mockInvitation",
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 100000
                )
        );
        owners.add(
                new ProjectMemberResponse(
                        "userId1",
                        "mockUsername1",
                        "mockEmail1@example.com",
                        "Mock Name 1",
                        "Mock Role 1",
                        "mockInvitation",
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 100000
                )
        );
        return owners;
    }

    public CommonResponse deleteProject(String projectID) throws IOException, LitmusApiException {
        return new CommonResponse("Project deleted successfully");
    }

    public SendInvitationResponse sendInvitation(SendInvitationRequest request) throws IOException, LitmusApiException {
        return new SendInvitationResponse(
                "mockUserId",
                "mockUsername",
                "Mock Name",
                "Mock Role",
                "mockEmail@example.com",
                "mockInvitation",
                "mockJoinedAt",
                "mockDeactivatedAt"
        );
    }

    public CommonResponse acceptInvitation(AcceptInvitationRequest request) throws IOException, LitmusApiException {
        // Mock implementation
        return new CommonResponse("Successful");
    }

    public CommonResponse declineInvitation(DeclineInvitationRequest request) throws IOException, LitmusApiException {
        // Mock implementation
        return new CommonResponse("Successful");
    }

    public CommonResponse removeInvitation(RemoveInvitationRequest request) throws IOException, LitmusApiException {
        // Mock implementation
        return new CommonResponse("Successful");
    }

    public List<ListInvitationResponse> listInvitation(String status) throws IOException, LitmusApiException {
        List<ListInvitationResponse> responses = new ArrayList<>();
        responses.add(
                new ListInvitationResponse(
                        "projectId1",
                        "mockProjectName1",
                        new ListInvitationResponse.ProjectUser(
                                "userId1",
                                "mockUsername1",
                                "mockEmail1@example.com",
                                "Mock Name 1",
                                "Mock Role 1",
                                "mockInvitation",
                                System.currentTimeMillis()
                        ),
                        "mockInvitationRole1"
                )
        );
        responses.add(
                new ListInvitationResponse(
                        "projectId1",
                        "mockProjectName1",
                        new ListInvitationResponse.ProjectUser(
                                "userId1",
                                "mockUsername1",
                                "mockEmail1@example.com",
                                "Mock Name 1",
                                "Mock Role 1",
                                "mockInvitation",
                                System.currentTimeMillis()
                        ),
                        "mockInvitationRole1"
                )
        );
        return responses;
    }

    public List<InviteUsersResponse> inviteUsers(String projectId) throws IOException, LitmusApiException {
        List<InviteUsersResponse> responses = new ArrayList<>();
        responses.add(
                new InviteUsersResponse(
                        System.currentTimeMillis(),
                        System.currentTimeMillis(),
                        new InviteUsersResponse.CreatedBy("creatorId2", "creatorUsername2", "creator2@example.com"),
                        new InviteUsersResponse.UpdatedBy("updaterId2", "updaterUsername2", "updater2@example.com"),
                        false,
                        "userId2",
                        "mockUsername2",
                        "mockSalt2",
                        "mockEmail2@example.com",
                        "Mock Name 2",
                        "Mock Role 2",
                        false
                )
        );
        responses.add(
                new InviteUsersResponse(
                        System.currentTimeMillis(),
                        System.currentTimeMillis() - 200000,
                        new InviteUsersResponse.CreatedBy("creatorId2", "creatorUsername2", "creator2@example.com"),
                        new InviteUsersResponse.UpdatedBy("updaterId2", "updaterUsername2", "updater2@example.com"),
                        false,
                        "userId2",
                        "mockUsername2",
                        "mockSalt2",
                        "mockEmail2@example.com",
                        "Mock Name 2",
                        "Mock Role 2",
                        false
                )
        );
        return responses;
    }

    public CommonResponse updateMemberRole(UpdateMemberRoleRequest request) throws IOException, LitmusApiException {
        // Mock implementation
        return null;
    }

    public StatusResponse status() throws IOException, LitmusApiException {
        // Mock implementation
        return null;
    }

    public ReadinessResponse readiness() throws IOException, LitmusApiException {
        // Mock implementation
        return null;
    }

    public Environment getEnvironment(GetEnvironmentGraphQLQuery query, GetEnvironmentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ListEnvironmentResponse listEnvironments(ListEnvironmentsGraphQLQuery query, ListEnvironmentsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public Environment createEnvironment(CreateEnvironmentGraphQLQuery query, CreateEnvironmentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public DeleteEnvironmentResponse deleteEnvironment(DeleteEnvironmentGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public UpdateEnvironmentResponse updateEnvironment(UpdateEnvironmentGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public Infra getInfra(GetInfraGraphQLQuery query, GetInfraProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ListInfraResponse listInfras(ListInfrasGraphQLQuery query, ListInfrasProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public Infra getInfraDetails(GetInfraDetailsGraphQLQuery query, GetInfraDetailsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public GetInfraStatsResponse getInfraStats(GetInfraDetailsGraphQLQuery query, GetInfraDetailsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public GetInfraManifestResponse getInfraManifest(GetInfraManifestGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public ConfirmInfraRegistrationResponse confirmInfraRegistration(ConfirmInfraRegistrationGraphQLQuery query, ConfirmInfraRegistrationProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public DeleteInfraResponse deleteInfra(DeleteInfraGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public RegisterInfraResponse registerInfra(RegisterInfraGraphQLQuery query, RegisterInfraProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public List<ChaosHubStatus> listChaosHub(ListChaosHubGraphQLQuery query, ListChaosHubProjectionRoot projectionRoot) {
        return null;
    }

    public ChaosHubStatus getChaosHub(GetChaosHubGraphQLQuery query, GetChaosHubProjectionRoot projectionRoot) {
        return null;
    }

    public GetChaosHubStatsResponse getChaosHubStats(GetChaosHubStatsGraphQLQuery query, GetChaosHubStatsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ChaosHub addChaosHub(AddChaosHubGraphQLQuery query, AddChaosHubProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ChaosHub addRemoteChaosHub(AddRemoteChaosHubGraphQLQuery query, AddRemoteChaosHubProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public DeleteChaosHubResponse deleteChaosHub(DeleteChaosHubGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public ChaosHub saveChaosHub(SaveChaosHubGraphQLQuery query, SaveChaosHubProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public SyncChaosHubResponse syncChaosHub(SyncChaosHubGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public ChaosHub updateChaosHub(UpdateChaosHubGraphQLQuery query, UpdateChaosHubProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public GetExperimentResponse getExperiment(GetExperimentGraphQLQuery query, GetExperimentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ListExperimentResponse listExperiment(ListExperimentGraphQLQuery query, ListExperimentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public GetExperimentStatsResponse getExperimentStats(GetExperimentStatsGraphQLQuery query, GetExperimentStatsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public List<PredefinedExperimentList> getPredefinedExperiment(GetPredefinedExperimentGraphQLQuery query, GetPredefinedExperimentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public List<PredefinedExperimentList> listPredefinedExperiments(ListPredefinedExperimentsGraphQLQuery query, ListPredefinedExperimentsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public RunChaosExperimentResponse runChaosExperiment(RunChaosExperimentGraphQLQuery query, RunChaosExperimentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public SaveChaosExperimentResponse saveChaosExperiment(SaveChaosExperimentGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public ChaosExperimentResponse updateChaosExperiment(UpdateChaosExperimentGraphQLQuery query, UpdateChaosExperimentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ChaosExperimentResponse createChaosExperiment(CreateChaosExperimentGraphQLQuery query, CreateChaosExperimentProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public DeleteChaosExperimentResponse deleteChaosExperiment(DeleteChaosExperimentGraphQLQuery query) {
        return null;
    }

    public UpdateCronExperimentStateResponse updateCronExperimentState(UpdateCronExperimentStateGraphQLQuery query) {
        return null;
    }

    public ExperimentRun getExperimentRun(GetExperimentRunGraphQLQuery query, GetExperimentRunProjectionRoot projectionRoot) {
        return null;
    }

    public GetExperimentRunStatsResponse getExperimentRunStats(GetExperimentRunStatsGraphQLQuery query, GetExperimentRunStatsProjectionRoot projectionRoot) {
        return null;
    }

    public ListExperimentRunResponse listExperimentRun(ListExperimentGraphQLQuery query, ListExperimentProjectionRoot projectionRoot) {
        return null;
    }

    public ChaosExperimentRunResponse chaosExperimentRun(ChaosExperimentRunGraphQLQuery query) {
        return null;
    }

    public StopExperimentRunsResponse stopExperimentRuns(StopExperimentRunsGraphQLQuery query) {
        return null;
    }

    public GitConfigResponse getGitOpsDetails(GetGitOpsDetailsGraphQLQuery query, GetGitOpsDetailsProjectionRoot projectionRoot) {
        return null;
    }

    public DisableGitOpsResponse disableGitOps(DisableGitOpsGraphQLQuery query) {
        return null;
    }

    public EnableGitOpsResponse enableGitOps(EnableGitOpsGraphQLQuery query) {
        return null;
    }

    public GitOpsNotifierResponse gitopsNotifier(GitopsNotifierGraphQLQuery query) {
        return null;
    }

    public UpdateGitOpsResponse updateGitOps(UpdateGitOpsGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public ImageRegistryResponse getImageRegistry(GetImageRegistryGraphQLQuery query, GetImageRegistryProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public List<ImageRegistryResponse> listImageRegistry(ListImageRegistryGraphQLQuery query, ListImageRegistryProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ImageRegistryResponse createImageRegistry(CreateImageRegistryGraphQLQuery query, CreateImageRegistryProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public DeleteImageRegistryResponse deleteImageRegistry(DeleteImageRegistryGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public ImageRegistryResponse updateImageRegistry(UpdateImageRegistryGraphQLQuery query, UpdateImageRegistryProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public List<Probe> listProbes(ListProbesGraphQLQuery query, ListProbesProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public Probe getProbe(GetProbeGraphQLQuery query, GetProbeProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ValidateUniqueProbeResponse validateUniqueProbe(ValidateUniqueProbeGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public GetProbeReferenceResponse getProbeReference(GetProbeReferenceGraphQLQuery query, GetProbeReferenceProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public GetProbeYAMLResponse getProbeYAML(GetProbeYAMLGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public GetProbesInExperimentRunResponse getProbesInExperimentRun(GetProbesInExperimentRunGraphQLQuery query, GetProbesInExperimentRunProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public Probe addProbe(AddProbeGraphQLQuery query, AddProbeProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public DeleteProbeResponse deleteProbe(DeleteProbeGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public UpdateProbeResponse updateProbe(UpdateProbeGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public FaultDetails getChaosFault(GetChaosFaultGraphQLQuery query, GetChaosFaultProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public List<Chart> listChaosFaults(ListChaosFaultsGraphQLQuery query, ListChaosFaultsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public ServerVersionResponse getServerVersion(GetServerVersionGraphQLQuery query, GetServerVersionProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public InfraVersionDetails getVersionDetails(GetVersionDetailsGraphQLQuery query, GetVersionDetailsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public SSHKey generateSSHKey(GenerateSSHKeyGraphQLQuery query, GenerateSSHKeyProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public GetManifestWithInfraIDResponse getManifestWithInfraID(GetManifestWithInfraIDGraphQLQuery query) {
        // Mock implementation
        return null;
    }

    public InfraEventResponse getInfraEvents(GetInfraEventsGraphQLQuery query, GetInfraEventsProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public KubeNamespaceResponse getKubeNamespace(GetKubeNamespaceGraphQLQuery query, GetKubeNamespaceProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public KubeObjectResponse getKubeObject(GetKubeObjectGraphQLQuery query, GetKubeObjectProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public PodLogResponse getPodLog(GetPodLogGraphQLQuery query, GetPodLogProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }

    public InfraActionResponse infraConnect(InfraConnectGraphQLQuery query, InfraConnectProjectionRoot projectionRoot) {
        // Mock implementation
        return null;
    }
}