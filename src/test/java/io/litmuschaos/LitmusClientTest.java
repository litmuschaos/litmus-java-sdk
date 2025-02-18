package io.litmuschaos;

import io.litmuschaos.enums.Role;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.client.*;
import io.litmuschaos.generated.types.*;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LitmusClientTest {

    private static final String HOST_URL = "http://localhost:53042"; // your frontend url here
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ4OTE1ODQwNzMsInJvbGUiOiJhZG1pbiIsInVpZCI6ImI1NWVlMzQwLWZiNzMtNDAyYy1hN2QwLWUxM2QzY2JiYTczOCIsInVzZXJuYW1lIjoiYWRtaW4ifQ.1bwzyAyAgW9ba7JgqaLXEomoEer-WtfyDaSqlwAdwLlKagt9lRjgaDcSm20YprsTqvM164eOSGu7FUhlOxZ81w"; // your API token here.

    private final LitmusClient litmusClient = new LitmusClient(HOST_URL,TEST_TOKEN);

    // authentication

    @Test
    void getTokens() throws IOException, LitmusApiException {
        String userId = "b55ee340-fb73-402c-a7d0-e13d3cbba738";
        ListTokensResponse response = litmusClient.getTokens(userId);
        assertThat(response).isInstanceOf(ListTokensResponse.class);
    }

    @Test
    void createToken() throws IOException, LitmusApiException {
        TokenCreateRequest request = TokenCreateRequest.builder()
                .userID("b55ee340-fb73-402c-a7d0-e13d3cbba738")
                .name("new token name")
                .daysUntilExpiration(36500)
                .build();
        TokenCreateResponse response = litmusClient.createToken(request);
        assertThat(response).isInstanceOf(TokenCreateResponse.class);
    }

    @Test
    void deleteToken() throws IOException, LitmusApiException {
        TokenDeleteRequest request = TokenDeleteRequest.builder()
                .token("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ4OTE5Mzg2NTksInJvbGUiOiJhZG1pbiIsInVpZCI6ImI1NWVlMzQwLWZiNzMtNDAyYy1hN2QwLWUxM2QzY2JiYTczOCIsInVzZXJuYW1lIjoiYWRtaW4ifQ.5iio28gGfk9PVzvDmMGenwlKvm4VTCPgSXama5FfaG1-6fCPFMMbGCymq2_4kHYsVITYyZfLnYDw-ywAYEsP2Q")
                .userID("b55ee340-fb73-402c-a7d0-e13d3cbba738")
                .build();
        CommonResponse response = litmusClient.deleteToken(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void getUser() throws IOException, LitmusApiException {
        String userId = "b55ee340-fb73-402c-a7d0-e13d3cbba738";
        UserResponse response = litmusClient.getUser(userId);
        assertThat(response).isInstanceOf(UserResponse.class);
    }

    @Test
    void getUsers() throws IOException, LitmusApiException {
        List<UserResponse> response = litmusClient.getUsers();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void updatePassword() throws IOException, LitmusApiException {
        PasswordUpdateRequest request = PasswordUpdateRequest.builder()
                .username("test")
                .oldPassword("old password")
                .newPassword("new password")
                .build();
        PasswordUpdateResponse response = litmusClient.updatePassword(request);
        assertThat(response).isInstanceOf(PasswordUpdateResponse.class);
    }

    @Test
    void createUser() throws IOException, LitmusApiException {
        UserCreateRequest request = UserCreateRequest.builder()
                .username("test_username")
                .password("test password")
                .role(Role.user) // Role can be admin, user
                .email("test@litmus.com")
                .build();
        UserResponse response = litmusClient.createUser(request);
        assertThat(response).isInstanceOf(UserResponse.class);

    }

    // only admin can call reset password
    @Test
    void resetPassword() throws IOException, LitmusApiException {
        PasswordResetRequest request = PasswordResetRequest.builder()
                .username("test_username")
                .newPassword("new password")
                .build();
        CommonResponse response = litmusClient.resetPassword(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void updateUserDetails() throws IOException, LitmusApiException {
        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .name("updated name")
                .email("updated@litmus.com")
                .build();
        CommonResponse response = litmusClient.updateUserDetails(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void updateUserState() throws IOException, LitmusApiException {
        UserStateUpdateRequest request = UserStateUpdateRequest.builder()
                .username("test_username")
                .isDeactivate(true)
                .build();
        CommonResponse response = litmusClient.updateUserState(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void capabilities() throws IOException, LitmusApiException {
        CapabilityResponse response = litmusClient.capabilities();
        assertThat(response).isInstanceOf(CapabilityResponse.class);
    }

    @Test
    void listProjects() throws IOException, LitmusApiException {
        ListProjectRequest request = ListProjectRequest.builder()
                .page(0)
                .limit(10)
                .sortField("name") // SortField can be name, time
                .createdByMe(true)
                .build();
        ListProjectsResponse response = litmusClient.listProjects(request);
        assertThat(response).isInstanceOf(ListProjectsResponse.class);
    }

    @Test
    void createProject() throws IOException, LitmusApiException {
        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectName("test projectName")
                .description("test description")
                .tags(List.of("tag1", "tag2"))
                .build();
        ProjectResponse response = litmusClient.createProject(request);
        assertThat(response).isInstanceOf(ProjectResponse.class);
    }

    @Test
    void updateProjectName() throws IOException, LitmusApiException {
        ProjectNameRequest request = ProjectNameRequest.builder()
                .projectID("02eb82e7-eafa-4528-8262-a49efd10c857")
                .projectName("test projectName 2")
                .build();
        CommonResponse response = litmusClient.updateProjectName(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void getProject() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        ProjectResponse response = litmusClient.getProject(projectID);
        assertThat(response).isInstanceOf(ProjectResponse.class);
    }

    @Test
    void getOwnerProjects() throws IOException, LitmusApiException {
        List<ProjectResponse> response = litmusClient.getOwnerProjects();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void leaveProject() throws IOException, LitmusApiException {
        LeaveProjectRequest request = LeaveProjectRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();
        CommonResponse response = litmusClient.leaveProject(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void getProjectRole() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        ProjectRoleResponse response = litmusClient.getProjectRole(projectID);
        assertThat(response).isInstanceOf(ProjectRoleResponse.class);
    }

    @Test
    void getUserWithProject() throws IOException, LitmusApiException {
        String username = "test_username";
        UserWithProjectResponse response = litmusClient.getUserWithProject(username);
        assertThat(response).isInstanceOf(UserWithProjectResponse.class);
    }

    @Test
    void getProjectsStats() throws IOException, LitmusApiException {
        List<ProjectsStatsResponse> response = litmusClient.getProjectsStats();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void getProjectMembers() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        String status = "accepted"; // Status can be accepted, not_accepted
        List<ProjectMemberResponse> response = litmusClient.getProjectMembers(projectID, status);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void getProjectOwners() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        List<ProjectMemberResponse> response = litmusClient.getProjectOwners(projectID);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void deleteProject() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        CommonResponse response = litmusClient.deleteProject(projectID);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void sendInvitation() throws IOException, LitmusApiException {
        SendInvitationRequest request = SendInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .role("Executor") // Role can be Owner, Executor, Viewer
                .build();

        SendInvitationResponse response = litmusClient.sendInvitation(request);
        assertThat(response).isInstanceOf(SendInvitationResponse.class);
    }

    @Test
    void acceptInvitation() throws IOException, LitmusApiException {
        AcceptInvitationRequest request = AcceptInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.acceptInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void declineInvitation() throws IOException, LitmusApiException {
        DeclineInvitationRequest request = DeclineInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.declineInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void removeInvitation() throws IOException, LitmusApiException {
        RemoveInvitationRequest request = RemoveInvitationRequest.builder()
                .projectId("02eb82e7-eafa-4528-8262-a49efd10c857")
                .userId("2f8bcfce-b4f3-475f-9e92-f852f99df29c")
                .build();

        CommonResponse response = litmusClient.removeInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void listInvitation() throws IOException, LitmusApiException {
        String status = "Accepted"; // Status can be Pending, Accepted, Declined, Exited
        List<ListInvitationResponse> response = litmusClient.listInvitation(status);
        assertThat(response).isInstanceOf(List.class);
    }

    // List of users that can be invited
    @Test
    void inviteUsers() throws IOException, LitmusApiException {
        String projectId = "02eb82e7-eafa-4528-8262-a49efd10c857";
        List<InviteUsersResponse> response = litmusClient.inviteUsers(projectId);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void updateMemberRole() throws IOException, LitmusApiException {
        UpdateMemberRoleRequest request = UpdateMemberRoleRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .role("Executor") // Role can be Owner, Executor, Viewer
                .build();

        CommonResponse response = litmusClient.updateMemberRole(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    void status() throws IOException, LitmusApiException {
        StatusResponse response = litmusClient.status();
        assertThat(response).isInstanceOf(StatusResponse.class);
    }

    @Test
    void readiness() throws IOException, LitmusApiException {
        ReadinessResponse response = litmusClient.readiness();
        assertThat(response).isInstanceOf(ReadinessResponse.class);
    }

    /*
     * GraphQL
     * You can select the response fields you want to return using an object graph called ProjectionRoot.
     * There are two ways to move to the top of the object graph: root(), parent()
     * - root(): Moves to the root of the object graph.
     * - parent(): Moves directly above in the object graph.
     */

    @Test
    void getEnvironment() throws LitmusApiException, IOException {
        GetEnvironmentGraphQLQuery query = new GetEnvironmentGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .environmentID("test-environments")
                .build();
        GetEnvironmentProjectionRoot projectionRoot = new GetEnvironmentProjectionRoot<>()
                .projectID()
                .name()
                .environmentID()
                .infraIDs()
                .updatedAt()
                .createdAt()
                .tags()
                .description()
                .isRemoved()
                .createdBy().userID().email().username().root() // after select inner object's field, you need to move above by using root() or parent()
                .updatedBy().userID().email().username().root()
                .type().root();

        Environment response = litmusClient.getEnvironment(query, projectionRoot);
        assertThat(response).isInstanceOf(Environment.class);
    }

    @Test
    void listEnvironments() throws LitmusApiException, IOException {
        ListEnvironmentsGraphQLQuery query = new ListEnvironmentsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(new ListEnvironmentRequest.Builder()
                      .environmentIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                      .filter(EnvironmentFilterInput.newBuilder()
                                .name("test")
                                .description("test")
                                .tags(List.of("tag1", "tag2"))
                                .type(EnvironmentType.PROD)
                                .build())
                        .pagination(Pagination.newBuilder().page(0).limit(10).build())
                        .sort(EnvironmentSortInput.newBuilder().ascending(true).field(EnvironmentSortingField.NAME).build())
                        .build()
                )
                .build();
        ListEnvironmentsProjectionRoot projectionRoot = new ListEnvironmentsProjectionRoot<>()
                .environments()
                .projectID()
                .environmentID()
                .createdAt()
                .updatedAt()
                .infraIDs()
                .name()
                .description()
                .tags()
                .isRemoved()
                .createdBy().userID().email().username().parent()
                .updatedBy().userID().email().username().parent()
                .type().root()
                .totalNoOfEnvironments();

        ListEnvironmentResponse response = litmusClient.listEnvironments(query, projectionRoot);
        assertThat(response).isInstanceOf(ListEnvironmentResponse.class);
    }

    @Test
    void createEnvironment() throws LitmusApiException, IOException {
        CreateEnvironmentGraphQLQuery query = new CreateEnvironmentGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(CreateEnvironmentRequest.newBuilder()
                        .environmentID("test-environments")
                        .tags(List.of("tag1", "tag2"))
                        .description("Test environment description")
                        .name("test-environments")
                        .type(EnvironmentType.NON_PROD)
                        .build()
                )
                .build();

        CreateEnvironmentProjectionRoot projectionRoot = new CreateEnvironmentProjectionRoot<>()
                .projectID()
                .name()
                .environmentID()
                .infraIDs()
                .updatedAt()
                .createdAt()
                .tags()
                .description()
                .isRemoved()
                .createdBy().userID().email().username().root() // after select inner object's field, you need to move above by using root() or parent()
                .updatedBy().userID().email().username().root()
                .type().root();


        Environment response = litmusClient.createEnvironment(query, projectionRoot);
        assertThat(response).isInstanceOf(Environment.class);
    }

    @Test
    void updateEnvironment() throws LitmusApiException, IOException {
        UpdateEnvironmentGraphQLQuery query = new UpdateEnvironmentGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(UpdateEnvironmentRequest.newBuilder()
                        .environmentID("test-environments")
                        .name("updated test-environments")
                        .tags(List.of("tag3", "tag4"))
                        .type(EnvironmentType.NON_PROD)
                        .description("Updated environment description")
                        .build()
                )
                .build();

        UpdateEnvironmentResponse response = litmusClient.updateEnvironment(query);
        assertThat(response).isInstanceOf(UpdateEnvironmentResponse.class);
    }

    @Test
    void deleteEnvironment() throws LitmusApiException, IOException {
        DeleteEnvironmentGraphQLQuery query = new DeleteEnvironmentGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .environmentID("test-environments")
                .build();

        DeleteEnvironmentResponse response = litmusClient.deleteEnvironment(query);
        assertThat(response).isInstanceOf(DeleteEnvironmentResponse.class);
    }

    @Test
    void getInfra() throws LitmusApiException, IOException {
        GetInfraGraphQLQuery query = new GetInfraGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
                .build();

        GetInfraProjectionRoot projectionRoot = new GetInfraProjectionRoot<>()
                .tags()
                .version()
                .name()
                .infraSaExists()
                .infraNsExists();

        Infra response = litmusClient.getInfra(query, projectionRoot);
        assertThat(response).isInstanceOf(Infra.class);
    }

    @Test
    void listInfras() throws LitmusApiException, IOException {
        ListInfrasGraphQLQuery query = new ListInfrasGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(new ListInfraRequest.Builder()
                        .environmentIDs(List.of("testing"))
                        .infraIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                        .filter(InfraFilterInput.newBuilder()
                                .name("test")
                                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                                .description("test")
                                .platformName("test")
                                .tags(List.of("tag1", "tag2"))
                                .isActive(true)
                                .infraScope(INFRA_SCOPE.cluster)
                                .build())
                        .pagination(Pagination.newBuilder().page(0).limit(10).build())
                        .build()
                )
                .build();

        ListInfrasProjectionRoot projectionRoot = new ListInfrasProjectionRoot<>()
                .infras()
                .projectID()
                .infraID()
                .createdAt()
                .updatedAt()
                .infraNamespace()
                .isInfraConfirmed()
                .name()
                .description()
                .tags()
                .isActive()
                .isRemoved()
                .infraScope()
                .environmentID()
                .platformName()
                .serviceAccount()
                .token()
                .version()
                .lastExperimentTimestamp()
                .noOfExperiments()
                .noOfExperimentRuns()
                .createdBy().userID().email().username().parent()
                .updatedBy().userID().email().username().root();

        ListInfraResponse response = litmusClient.listInfras(query, projectionRoot);
        assertThat(response).isInstanceOf(ListInfraResponse.class);
    }

    @Test
    void getInfraDetails() throws LitmusApiException, IOException {
        GetInfraDetailsGraphQLQuery query = new GetInfraDetailsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .infraID("a53f0ffc-d8df-4963-8701-c1b6de179531")
                .build();

        GetInfraDetailsProjectionRoot projectionRoot = new GetInfraDetailsProjectionRoot<>()
                .infraID()
                .infraNamespace()
                .description()
                .environmentID()
                .infraScope()
                .infraType().root()
                .isActive()
                .isRemoved()
                .isInfraConfirmed()
                .lastExperimentTimestamp()
                .name()
                .noOfExperimentRuns()
                .noOfExperiments()
                .platformName()
                .projectID()
                .serviceAccount()
                .startTime()
                .tags()
                .token()
                .version()
                .createdAt()
                .updatedAt()
                .createdBy().userID().email().username().root()
                .updatedBy().userID().email().username().root();

        Infra response = litmusClient.getInfraDetails(query, projectionRoot);
        assertThat(response).isInstanceOf(Infra.class);
    }

    @Test
    void getInfraStats() throws LitmusApiException, IOException {
        GetInfraStatsGraphQLQuery query = new GetInfraStatsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetInfraStatsProjectionRoot projectionRoot = new GetInfraStatsProjectionRoot<>()
                .totalActiveInfrastructure()
                .totalConfirmedInfrastructure()
                .totalInactiveInfrastructures()
                .totalInfrastructures()
                .totalNonConfirmedInfrastructures();


        GetInfraStatsResponse response = litmusClient.getInfraStats(query, projectionRoot);
        assertThat(response).isInstanceOf(GetInfraStatsResponse.class);
    }

    // TODO: NOT TESTED, it is used in litmus but when i call API, result not exist
    @Test
    void getInfraManifest() throws LitmusApiException, IOException {
        GetInfraManifestGraphQLQuery query = new GetInfraManifestGraphQLQuery.Builder()
                .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .upgrade(true)
                .build();

        GetInfraManifestResponse response = litmusClient.getInfraManifest(query);
        assertThat(response).isInstanceOf(GetInfraManifestResponse.class);

    }

    // TODO: NOT TESTED
    @Test
    void confirmInfraRegistration() throws LitmusApiException, IOException {
        ConfirmInfraRegistrationGraphQLQuery query = new ConfirmInfraRegistrationGraphQLQuery.Builder()
                .request(InfraIdentity.newBuilder()
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .accessKey("test access key")
                        .version("test version")
                        .build())
                .build();

        ConfirmInfraRegistrationProjectionRoot projectionRoot = new ConfirmInfraRegistrationProjectionRoot<>()
                .infraID()
                .isInfraConfirmed()
                .newAccessKey();


        ConfirmInfraRegistrationResponse response = litmusClient.confirmInfraRegistration(query, projectionRoot);
        assertThat(response).isInstanceOf(ConfirmInfraRegistrationResponse.class);
    }

    @Test
    void deleteInfra() throws LitmusApiException, IOException {
        DeleteInfraGraphQLQuery query = new DeleteInfraGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .infraID("a53f0ffc-d8df-4963-8701-c1b6de179531")
                .build();

        DeleteInfraResponse response = litmusClient.deleteInfra(query);
        assertThat(response).isInstanceOf(DeleteInfraResponse.class);
    }

    @Test
    void registerInfra() throws LitmusApiException, IOException {
        RegisterInfraGraphQLQuery query = new RegisterInfraGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(RegisterInfraRequest.newBuilder()
                        .description("test description")
                        .environmentID("testing")
                        .infraNamespace("litmus")
                        .infraNsExists(false)
                        .infraSaExists(false)
                        .infraScope(INFRA_SCOPE.cluster.name())
                        .infrastructureType(InfrastructureType.Kubernetes)
                        .name("test3")
                      //  .nodeSelector("test node selector")
                        .platformName("Kubernetes")
                        .serviceAccount("litmus")
                        .skipSsl(false)
                        .tags(List.of("tag1", "tag2"))
                      //  .tolerations(List.of(Toleration.newBuilder().value("test value").key("test key").effect("test effect").build()))
                        .build()
                )
                .build();

        RegisterInfraProjectionRoot projectionRoot = new RegisterInfraProjectionRoot<>()
                .manifest();

        RegisterInfraResponse response = litmusClient.registerInfra(query, projectionRoot);
        assertThat(response).isInstanceOf(RegisterInfraResponse.class);
    }

    @Test
    void listChaosHub() throws LitmusApiException, IOException {
        ListChaosHubGraphQLQuery query = new ListChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(new ListChaosHubRequest.Builder()
                        .chaosHubIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                        .filter(ChaosHubFilterInput.newBuilder()
                                .chaosHubName("test")
                                .tags(List.of("tag1", "tag2"))
                                .description("test")
                                .build())
                        .build()
                )
                .build();

        ListChaosHubProjectionRoot projectionRoot = new ListChaosHubProjectionRoot<>()
                .name()
                .description()
                .id()
                .repoURL()
                .isAvailable()
                .isDefault()
                .isPrivate();

        List<ChaosHubStatus> response = litmusClient.listChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void getChaosHub() throws LitmusApiException, IOException {
        GetChaosHubGraphQLQuery query = new GetChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .chaosHubID("218fb866-8406-4397-996a-36b75416d683")
                .build();

        GetChaosHubProjectionRoot projectionRoot = new GetChaosHubProjectionRoot<>()
                .name()
                .description()
                .id()
                .repoURL()
                .isAvailable()
                .isDefault()
                .isPrivate();

        ChaosHubStatus response = litmusClient.getChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosHubStatus.class);
    }

    @Test
    void getChaosHubStats() throws LitmusApiException, IOException {
        GetChaosHubStatsGraphQLQuery query = new GetChaosHubStatsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetChaosHubStatsProjectionRoot projectionRoot = new GetChaosHubStatsProjectionRoot<>()
                .totalChaosHubs();

        GetChaosHubStatsResponse response = litmusClient.getChaosHubStats(query, projectionRoot);
        assertThat(response).isInstanceOf(GetChaosHubStatsResponse.class);
    }

    @Test
    void addChaosHub() throws LitmusApiException, IOException {
        AddChaosHubGraphQLQuery query = new AddChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(CreateChaosHubRequest.newBuilder()
                        .remoteHub("GitHub")
                        .name("test chaoshub")
                        .authType(AuthType.NONE)
                        .description("test chaosHub description")
                        .isPrivate(false)
                        .tags(List.of("tag1", "tag2"))
                        .repoBranch("master")
                        .repoURL("https://www.github.com")
                        .build()
                )
                .build();

        AddChaosHubProjectionRoot projectionRoot = new AddChaosHubProjectionRoot<>()
                .remoteHub()
                .projectID()
                .description()
                .isDefault();

        ChaosHub response = litmusClient.addChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosHub.class);
    }

    // TODO: NOT TESTED, may not used
    @Test
    void addRemoteChaosHub() throws LitmusApiException, IOException {
        AddRemoteChaosHubGraphQLQuery query = new AddRemoteChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(CreateRemoteChaosHub.newBuilder()
                        .remoteHub("test remote hub")
                        .description("test description")
                        .name("test name")
                        .repoURL("test repo url")
                        .tags(List.of("tag1", "tag2"))
                        .build()
                )
                .build();

        AddRemoteChaosHubProjectionRoot projectionRoot = new AddRemoteChaosHubProjectionRoot<>()
                .remoteHub()
                .projectID()
                .description()
                .isDefault();

        ChaosHub response = litmusClient.addRemoteChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosHub.class);
    }

    @Test
    void deleteChaosHub() throws LitmusApiException, IOException {
        DeleteChaosHubGraphQLQuery query = new DeleteChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .hubID("218fb866-8406-4397-996a-36b75416d683")
                .build();

        DeleteChaosHubResponse response = litmusClient.deleteChaosHub(query);
        assertThat(response).isInstanceOf(DeleteChaosHubResponse.class);
    }

    @Test
    void saveChaosHub() throws LitmusApiException, IOException {
        SaveChaosHubGraphQLQuery query = new SaveChaosHubGraphQLQuery.Builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(CreateChaosHubRequest.newBuilder()
                        .repoBranch("test repo branch")
                        .tags(List.of("tag1", "tag2"))
                        .isPrivate(true)
                        .authType(AuthType.BASIC)
                        .description("test description")
                        .build()
                )
                .build();

        SaveChaosHubProjectionRoot projectionRoot = new SaveChaosHubProjectionRoot<>()
                .remoteHub()
                .projectID()
                .description()
                .isDefault();

        ChaosHub response = litmusClient.saveChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosHub.class);
    }

    @Test
    void syncChaosHub() throws LitmusApiException, IOException {
        SyncChaosHubGraphQLQuery query = new SyncChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .id("218fb866-8406-4397-996a-36b75416d683")
                .build();

        SyncChaosHubResponse response = litmusClient.syncChaosHub(query);
        assertThat(response).isInstanceOf(SyncChaosHubResponse.class);
    }

    @Test
    void updateChaosHub() throws LitmusApiException, IOException {
        UpdateChaosHubGraphQLQuery query = new UpdateChaosHubGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(UpdateChaosHubRequest.newBuilder()
                        .id("218fb866-8406-4397-996a-36b75416d683") // chaos hub ID
                        .repoBranch("test repo branch")
                        .tags(List.of("tag1", "tag2"))
                        .isPrivate(true)
                        .authType(AuthType.BASIC)
                        .description("test description")
                        .build()
                )
                .build();

        UpdateChaosHubProjectionRoot projectionRoot = new UpdateChaosHubProjectionRoot<>()
                .remoteHub()
                .projectID()
                .description()
                .isDefault();

        ChaosHub response = litmusClient.updateChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosHub.class);
    }

    @Test
    void getExperiment() throws LitmusApiException, IOException {
        GetExperimentGraphQLQuery query = new GetExperimentGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .experimentID("0eab377f-d8dc-491d-af40-dfebf110b4fe")
                .build();

        GetExperimentProjectionRoot projectionRoot = new GetExperimentProjectionRoot<>()
                .averageResiliencyScore()
                .experimentDetails()
                .createdAt()
                .name()
                .tags()
                .projectID()
                .root();

        GetExperimentResponse response = litmusClient.getExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(GetExperimentResponse.class);
    }

    @Test
    void listExperiment() throws LitmusApiException, IOException {
        ListExperimentGraphQLQuery query = new ListExperimentGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .request(new ListExperimentRequest.Builder()
                        .experimentIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                        .filter(ExperimentFilterInput.newBuilder()
                                .dateRange(DateRange.newBuilder()
                                        .startDate("1737817199000")
                                        .endDate("1733929200000")
                                        .build())
                                .build()

                        )
                        .pagination(Pagination.newBuilder().page(0).limit(10).build())
                        .sort(ExperimentSortInput.newBuilder().ascending(true).field(ExperimentSortingField.NAME).build())
                        .build()
                )
                .build();

        ListExperimentProjectionRoot projectionRoot = new ListExperimentProjectionRoot<>()
                .experiments()
                .projectID()
                .experimentID()
                .createdAt()
                .updatedAt()
                .name()
                .description()
                .tags()
                .isRemoved()
                .createdBy().userID().email().username().parent()
                .updatedBy().userID().email().username().parent()
                .root()
                .totalNoOfExperiments();

        ListExperimentResponse response = litmusClient.listExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(ListExperimentResponse.class);
    }

    @Test
    void getExperimentStats() throws LitmusApiException, IOException {
        GetExperimentStatsGraphQLQuery query = new GetExperimentStatsGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .build();

        GetExperimentStatsProjectionRoot projectionRoot = new GetExperimentStatsProjectionRoot<>()
                .totalExperiments()
                .totalExpCategorizedByResiliencyScore().count().root();

        GetExperimentStatsResponse response = litmusClient.getExperimentStats(query, projectionRoot);
        assertThat(response).isInstanceOf(GetExperimentStatsResponse.class);
    }

    @Test
    void getPredefinedExperiment() throws LitmusApiException, IOException {
        GetPredefinedExperimentGraphQLQuery query = new GetPredefinedExperimentGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .experimentName(List.of("Node CPU Hog"))
                .hubID("6f39cea9-6264-4951-83a8-29976b614289")
                .build();

        GetPredefinedExperimentProjectionRoot projectionRoot = new GetPredefinedExperimentProjectionRoot<>()
                .experimentCSV()
                .experimentName()
                .experimentManifest();

        List<PredefinedExperimentList> response = litmusClient.getPredefinedExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void listPredefinedExperiments() throws LitmusApiException, IOException {
        ListPredefinedExperimentsGraphQLQuery query = new ListPredefinedExperimentsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .hubID("6f39cea9-6264-4951-83a8-29976b614289")
                .build();

        ListPredefinedExperimentsProjectionRoot projectionRoot = new ListPredefinedExperimentsProjectionRoot<>()
                .experimentCSV()
                .experimentName()
                .experimentManifest();

        List<PredefinedExperimentList> response = litmusClient.listPredefinedExperiments(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void runChaosExperiment() throws LitmusApiException, IOException {
        RunChaosExperimentGraphQLQuery query = new RunChaosExperimentGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .experimentID("1af067d5-fec7-4117-92df-036f5a571372")
                .build();

        RunChaosExperimentProjectionRoot projectionRoot = new RunChaosExperimentProjectionRoot<>()
                .notifyID();

        RunChaosExperimentResponse response = litmusClient.runChaosExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(RunChaosExperimentResponse.class);
    }

    @Test
    void saveChaosExperiment() throws LitmusApiException, IOException {
        SaveChaosExperimentGraphQLQuery query = new SaveChaosExperimentGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .request(SaveChaosExperimentRequest.newBuilder()
                        .description("test description")
                        .id("36048cb8-19db-40f1-8f09-20313b576c22")
                        .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
                        .manifest("manifest file")
                        .tags(List.of("tag1", "tag2"))
                        .name("test2-experiment")
                        .build())
                .build();


        SaveChaosExperimentResponse response = litmusClient.saveChaosExperiment(query);
        assertThat(response).isInstanceOf(SaveChaosExperimentResponse.class);
    }

    @Test
    void updateChaosExperiment() throws LitmusApiException, IOException {
        UpdateChaosExperimentGraphQLQuery query = new UpdateChaosExperimentGraphQLQuery.Builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(ChaosExperimentRequest.newBuilder()
                        .experimentManifest("test manifest")
                        .runExperiment(true)
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .tags(List.of("tag1", "tag2"))
                        .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .build())
                .build();

        UpdateChaosExperimentProjectionRoot projectionRoot = new UpdateChaosExperimentProjectionRoot<>()
                .experimentID()
                .projectID()
                .experimentName();

        ChaosExperimentResponse response = litmusClient.updateChaosExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosExperimentResponse.class);
    }

    // TODO: NOT TESTED, may be not used
    @Test
    void createChaosExperiment() throws LitmusApiException, IOException {
        CreateChaosExperimentGraphQLQuery query = new CreateChaosExperimentGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .request(
                        ChaosExperimentRequest.newBuilder()
                        .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
                        .experimentID("fbbd6d12-bd3e-4b8f-a857-023691251228")
                                .experimentManifest("test manifest")
                        .tags(List.of("tag1", "tag2"))
                        .runExperiment(true)
                        .experimentID("fbbd6d12-bd3e-4b8f-a857-023691251228")
                        .isCustomExperiment(false)
                        .experimentDescription("test description")
                        .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
                        .build()
                )
                .build();

        CreateChaosExperimentProjectionRoot projectionRoot = new CreateChaosExperimentProjectionRoot<>()
                .experimentID()
                .projectID()
                .experimentName();

        ChaosExperimentResponse response = litmusClient.createChaosExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosExperimentResponse.class);
    }

    @Test
    void deleteChaosExperiment() throws LitmusApiException, IOException {
        DeleteChaosExperimentGraphQLQuery query = new DeleteChaosExperimentGraphQLQuery.Builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentRunID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        DeleteChaosExperimentResponse response = litmusClient.deleteChaosExperiment(query);
        assertThat(response).isInstanceOf(DeleteChaosExperimentResponse.class);
    }

    @Test
    void updateCronExperimentState() throws LitmusApiException, IOException {
        UpdateCronExperimentStateGraphQLQuery query = new UpdateCronExperimentStateGraphQLQuery.Builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .disable(true)
                .build();

        UpdateCronExperimentStateResponse response = litmusClient.updateCronExperimentState(query);
        assertThat(response).isInstanceOf(UpdateCronExperimentStateResponse.class);
    }

    // Chaos Experiment Run
    @Test
    void getExperimentRun() throws LitmusApiException, IOException {
        GetExperimentRunGraphQLQuery query = new GetExperimentRunGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .notifyID("7502028c-1103-4c54-8ac3-e5b5ec9b49f5")
                .build();

        GetExperimentRunProjectionRoot projectionRoot = new GetExperimentRunProjectionRoot<>()
                .executionData()
                .experimentID()
                .experimentManifest()
                .experimentName()
                .experimentRunID()
                .experimentType()
                .notifyID()
                .phase().root()
                .resiliencyScore()
                .runSequence();

        ExperimentRun response = litmusClient.getExperimentRun(query, projectionRoot);
        assertThat(response).isInstanceOf(ExperimentRun.class);

    }

    @Test
    void getExperimentRunStats() throws LitmusApiException, IOException {
        GetExperimentRunStatsGraphQLQuery query = new GetExperimentRunStatsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetExperimentRunStatsProjectionRoot projectionRoot = new GetExperimentRunStatsProjectionRoot<>()
                .totalCompletedExperimentRuns()
                .totalRunningExperimentRuns()
                .totalExperimentRuns()
                .totalErroredExperimentRuns()
                .totalStoppedExperimentRuns()
                .totalTerminatedExperimentRuns();

        GetExperimentRunStatsResponse response = litmusClient.getExperimentRunStats(query, projectionRoot);
        assertThat(response).isInstanceOf(GetExperimentRunStatsResponse.class);
    }

    @Test
    void listExperimentRun() throws LitmusApiException, IOException {
        ListExperimentRunGraphQLQuery query = new ListExperimentRunGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(new ListExperimentRunRequest.Builder()
                        .experimentRunIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                        .filter(ExperimentRunFilterInput.newBuilder()
                                .experimentRunID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                                .experimentName("test")
                                .experimentRunStatus(List.of(ExperimentRunStatus.Running.name()))
                                .dateRange(DateRange.newBuilder()
                                        .startDate("1737817199000")
                                        .endDate("1737817199900")
                                        .build())
                                .build()
                        )
                        .pagination(Pagination.newBuilder().page(0).limit(10).build())
                        .sort(ExperimentRunSortInput.newBuilder().ascending(true).field(ExperimentSortingField.NAME).build())
                        .build()
                )
                .build();

        ListExperimentRunProjectionRoot projectionRoot = new ListExperimentRunProjectionRoot<>()
                .experimentRuns()
                .projectID()
                .executionData().root();

        ListExperimentRunResponse response = litmusClient.listExperimentRun(query, projectionRoot);
        assertThat(response).isInstanceOf(ListExperimentRunResponse.class);
    }

    // TODO: NOT TESTED, may be not used
    @Test
    void chaosExperimentRun() throws LitmusApiException, IOException {
        ChaosExperimentRunGraphQLQuery query = new ChaosExperimentRunGraphQLQuery.Builder()
                .request(ExperimentRunRequest.newBuilder()
                        .experimentID("1af067d5-fec7-4117-92df-036f5a571372")
                        .experimentRunID("43bc2abd-bb9f-431a-95d9-458b380b5e1a")
                        .experimentName("test-experiment")
                        .completed(false)
                        .build())
                .build();

        ChaosExperimentRunResponse response = litmusClient.chaosExperimentRun(query);
        assertThat(response).isInstanceOf(ChaosExperimentRunResponse.class);
    }

    @Test
    void stopExperimentRuns() throws LitmusApiException, IOException {
        StopExperimentRunsGraphQLQuery query = new StopExperimentRunsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .experimentID("1af067d5-fec7-4117-92df-036f5a571372")
                .build();

        StopExperimentRunsResponse response = litmusClient.stopExperimentRuns(query);
        assertThat(response).isInstanceOf(StopExperimentRunsResponse.class);
    }

    // GitOps
    @Test
    void getGitOpsDetails() throws LitmusApiException, IOException {
        GetGitOpsDetailsGraphQLQuery query = new GetGitOpsDetailsGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .build();

        GetGitOpsDetailsProjectionRoot projectionRoot = new GetGitOpsDetailsProjectionRoot<>()
                .projectID()
                .branch()
                .repoURL();

        GitConfigResponse response = litmusClient.getGitOpsDetails(query, projectionRoot);
        assertThat(response).isInstanceOf(GitConfigResponse.class);
    }

    @Test
    void disableGitOps() throws LitmusApiException, IOException {
        DisableGitOpsGraphQLQuery query = new DisableGitOpsGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .build();

        DisableGitOpsResponse response = litmusClient.disableGitOps(query);
        assertThat(response).isInstanceOf(DisableGitOpsResponse.class);
    }

    // TODO: NOT TESTED
    @Test
    void enableGitOps() throws LitmusApiException, IOException {
        EnableGitOpsGraphQLQuery query = new EnableGitOpsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .configurations(GitConfig.newBuilder()
                        .authType(AuthType.TOKEN) // BASIC, NONE, SSH, TOKEN
                        .branch("test")
                        .password("test")
                        .repoURL("test")
                        .sshPrivateKey("test")
                .build())
                .build();

        EnableGitOpsResponse response = litmusClient.enableGitOps(query);
        assertThat(response).isInstanceOf(EnableGitOpsResponse.class);
    }

    // TODO: NOT TESTED
    @Test
    void gitopsNotifier() throws LitmusApiException, IOException {
        GitopsNotifierGraphQLQuery query = new GitopsNotifierGraphQLQuery.Builder()
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .clusterInfo(InfraIdentity.newBuilder()
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .accessKey("test access key")
                        .version("test version")
                        .build())
                .build();

        GitOpsNotifierResponse response = litmusClient.gitopsNotifier(query);
        assertThat(response).isInstanceOf(GitOpsNotifierResponse.class);
    }

    // TODO: NOT TESTED
    @Test
    void updateGitOps() throws LitmusApiException, IOException {
        UpdateGitOpsGraphQLQuery query = new UpdateGitOpsGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .configurations(GitConfig.newBuilder()
                        .authType(AuthType.NONE)
                        .branch("test")
                        .password("test")
                        .repoURL("test")
                        .sshPrivateKey("test")
                        .build())
                .build();

        UpdateGitOpsResponse response = litmusClient.updateGitOps(query);
        assertThat(response).isInstanceOf(UpdateGitOpsResponse.class);
    }

    // Image Registry
    @Test
    void getImageRegistry() throws LitmusApiException, IOException {
        GetImageRegistryGraphQLQuery query = new GetImageRegistryGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .build();

        GetImageRegistryProjectionRoot projectionRoot = new GetImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID()
                .isRemoved();

        ImageRegistryResponse response = litmusClient.getImageRegistry(query, projectionRoot);
        assertThat(response).isInstanceOf(ImageRegistryResponse.class);
    }

    @Test
    void listImageRegistry() throws LitmusApiException, IOException {
        ListImageRegistryGraphQLQuery query = new ListImageRegistryGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .build();

        ListImageRegistryProjectionRoot projectionRoot = new ListImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID();

        List<ImageRegistryResponse> response = litmusClient.listImageRegistry(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    // TODO: NOT TESTED
    @Test
    void createImageRegistry() throws LitmusApiException, IOException {
        CreateImageRegistryGraphQLQuery query = new CreateImageRegistryGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .imageRegistryInfo(ImageRegistryInput.newBuilder()
                        .enableRegistry(true)
                        .imageRegistryName("docker.io")
                        .imageRegistryType("public")
                        .imageRepoName("litmuschaos")
                        .isDefault(false)
                        .build())
                .build();

        CreateImageRegistryProjectionRoot projectionRoot = new CreateImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID();

        ImageRegistryResponse response = litmusClient.createImageRegistry(query, projectionRoot);
        assertThat(response).isInstanceOf(ImageRegistryResponse.class);
    }

    // TODO: NOT TESTED
    @Test
    void deleteImageRegistry() throws LitmusApiException, IOException {
        DeleteImageRegistryGraphQLQuery query = new DeleteImageRegistryGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .imageRegistryID("c4f670bd-8b23-4ef0-a21f-f3a098b5b878")
                .build();

        DeleteImageRegistryResponse response = litmusClient.deleteImageRegistry(query);
        assertThat(response).isInstanceOf(DeleteImageRegistryResponse.class);
    }

    // TODO: NOT TESTED
    @Test
    void updateImageRegistry() throws LitmusApiException, IOException {
        UpdateImageRegistryGraphQLQuery query = new UpdateImageRegistryGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .imageRegistryID("c4f670bd-8b23-4ef0-a21f-f3a098b5b878")
                .imageRegistryInfo(ImageRegistryInput.newBuilder()
                        .enableRegistry(true)
                        .imageRegistryName("docker.io")
                        .imageRegistryType("public")
                        .imageRepoName("litmuschaos")
                        .isDefault(true)
                        .build())
                .build();

        UpdateImageRegistryProjectionRoot projectionRoot = new UpdateImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID();

        ImageRegistryResponse response = litmusClient.updateImageRegistry(query, projectionRoot);
        assertThat(response).isInstanceOf(ImageRegistryResponse.class);
    }

    // Probe
    @Test
    void listProbes() throws LitmusApiException, IOException {
        ListProbesGraphQLQuery query = new ListProbesGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .infrastructureType(InfrastructureType.Kubernetes) // type is Kubernetes
                .filter(ProbeFilterInput.newBuilder()
                        .dateRange(DateRange.newBuilder()
                                .startDate("1737039599000") // timestamp
                                .endDate("1734361200000") // timestamp
                                .build())
                        .type(List.of(ProbeType.cmdProbe)) // httpProbe, cmdProbe, promProbe, k8sProbe
                        .build())
                .build();

        ListProbesProjectionRoot projectionRoot = new ListProbesProjectionRoot<>()
                .projectID()
                .name()
                .tags()
                .description();

        List<Probe> response = litmusClient.listProbes(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void getProbe() throws LitmusApiException, IOException {
        GetProbeGraphQLQuery query = new GetProbeGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .probeName("test-probe")
                .build();

        GetProbeProjectionRoot projectionRoot = new GetProbeProjectionRoot<>()
                .name()
                .description()
                .type().root()
                .infrastructureType().root()
                .createdAt()
                .updatedAt();

        Probe response = litmusClient.getProbe(query, projectionRoot);
        assertThat(response).isInstanceOf(Probe.class);
    }

    @Test
    void validateUniqueProbe() throws LitmusApiException, IOException {
        ValidateUniqueProbeGraphQLQuery query = new ValidateUniqueProbeGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .probeName("test-probe")
                .build();

        ValidateUniqueProbeResponse response = litmusClient.validateUniqueProbe(query);
        assertThat(response).isInstanceOf(ValidateUniqueProbeResponse.class);
    }

    @Test
    void getProbeReference() throws LitmusApiException, IOException { // we need create long type to graphql for updatedAt timestamp field
        GetProbeReferenceGraphQLQuery query = new GetProbeReferenceGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .probeName("test-probe")
                .build();

        GetProbeReferenceProjectionRoot projectionRoot = new GetProbeReferenceProjectionRoot<>()
                .name()
                .recentExecutions()
                .faultName()
                .mode().parent()
                .executionHistory()
                .executedByExperiment().experimentID().experimentName().updatedBy().username().email().parent().parent()
                .status().verdict().root()
                .totalRuns();

        GetProbeReferenceResponse response = litmusClient.getProbeReference(query, projectionRoot);
        assertThat(response).isInstanceOf(GetProbeReferenceResponse.class);
    }

    @Test
    void getProbeYAML() throws LitmusApiException, IOException {
        GetProbeYAMLGraphQLQuery query = new GetProbeYAMLGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(GetProbeYAMLRequest.newBuilder()
                        .probeName("test-probe")
                        .mode(Mode.EOT) // SOT, EOT, Edge, Continuous, OnChaos
                        .build())
                .build();

        GetProbeYAMLResponse response = litmusClient.getProbeYAML(query);
        assertThat(response).isInstanceOf(GetProbeYAMLResponse.class);
    }

    @Test
    void getProbesInExperimentRun() throws LitmusApiException, IOException {
        GetProbesInExperimentRunGraphQLQuery query = new GetProbesInExperimentRunGraphQLQuery.Builder()
                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
                .experimentRunID("cd368e9b-1ac1-4606-8f62-65af6a5d838b")
                .faultName("pod-delete-yqr")
                .build();

        GetProbesInExperimentRunProjectionRoot projectionRoot = new GetProbesInExperimentRunProjectionRoot<>()
                .probe().projectID().description().root();

        List<GetProbesInExperimentRunResponse> response = litmusClient.getProbesInExperimentRun(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    void addProbe() throws LitmusApiException, IOException {
        AddProbeGraphQLQuery query = new AddProbeGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(ProbeRequest.newBuilder()
                        .description("test description")
                        .infrastructureType(InfrastructureType.Kubernetes)
                        .name("test name")
                        .tags(List.of("tag1", "tag2"))
                        .type(ProbeType.cmdProbe)
                        .kubernetesCMDProperties(KubernetesCMDProbeRequest.newBuilder()
                                .attempt(1)
                                .command("test")
                                .interval("2s")
                                .probeTimeout("10s")
                                .source("test")
                                .comparator(ComparatorInput.newBuilder()
                                        .criteria("test")
                                        .type("int")
                                        .value("0")
                                        .build())
                                .build())
                        .build())
                .build();

        AddProbeProjectionRoot projectionRoot = new AddProbeProjectionRoot<>()
                .name()
                .projectID()
                .tags();

        Probe response = litmusClient.addProbe(query, projectionRoot);
        assertThat(response).isInstanceOf(Probe.class);
    }

    @Test
    void deleteProbe() throws LitmusApiException, IOException {
        DeleteProbeGraphQLQuery query = new DeleteProbeGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .probeName("test name")
                .build();

        DeleteProbeResponse response = litmusClient.deleteProbe(query);
        assertThat(response).isInstanceOf(DeleteProbeResponse.class);
    }

    @Test
    void updateProbe() throws LitmusApiException, IOException {
        UpdateProbeGraphQLQuery query = new UpdateProbeGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(ProbeRequest.newBuilder()
                        .description("test description")
                        .type(ProbeType.cmdProbe)
                        .infrastructureType(InfrastructureType.Kubernetes)
                        .name("cmd-probe")
                        .tags(List.of("tag1", "tag2"))
                        .kubernetesCMDProperties(KubernetesCMDProbeRequest.newBuilder()
                                .attempt(1)
                                .command("test")
                                .interval("4s")
                                .probeTimeout("10s")
                                .source("test")
                                .comparator(ComparatorInput.newBuilder()
                                        .criteria("test")
                                        .type("int")
                                        .value("0")
                                        .build())
                                .build())
                        .build())
                .build();

        UpdateProbeResponse response = litmusClient.updateProbe(query);
        assertThat(response).isInstanceOf(UpdateProbeResponse.class);
    }

    // chaos fault
    @Test
    void getChaosFault() throws LitmusApiException, IOException {
        GetChaosFaultGraphQLQuery query = new GetChaosFaultGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(ExperimentRequest.newBuilder()
                        .category("aws")
                        .experimentName("aws-ssm-chaos-by-tag")
                        .hubID("6f39cea9-6264-4951-83a8-29976b614289")
                        .build())
                .build();

        GetChaosFaultProjectionRoot projectionRoot = new GetChaosFaultProjectionRoot<>()
                .fault()
                .engine()
                .csv();

        FaultDetails response = litmusClient.getChaosFault(query, projectionRoot);
        assertThat(response).isInstanceOf(FaultDetails.class);
    }

    @Test
    void listChaosFaults() throws LitmusApiException, IOException {
        ListChaosFaultsGraphQLQuery query = new ListChaosFaultsGraphQLQuery.Builder()
                .hubID("6f39cea9-6264-4951-83a8-29976b614289")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        ListChaosFaultsProjectionRoot projectionRoot = new ListChaosFaultsProjectionRoot<>()
                .apiVersion()
                .kind()
                .metadata().annotations().categories().chartDescription().repository().support().vendor().root()
                .packageInfo().packageName().experiments().CSV().root();

        List<Chart> response = litmusClient.listChaosFaults(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    // others
    @Test
    void getServerVersion() throws LitmusApiException, IOException {
        GetServerVersionGraphQLQuery query = new GetServerVersionGraphQLQuery.Builder()
                .build();

        GetServerVersionProjectionRoot projectionRoot = new GetServerVersionProjectionRoot<>()
                .key()
                .value();

        ServerVersionResponse response = litmusClient.getServerVersion(query, projectionRoot);
        assertThat(response).isInstanceOf(ServerVersionResponse.class);
    }

    @Test
    void getVersionDetails() throws LitmusApiException, IOException {
        GetVersionDetailsGraphQLQuery query = new GetVersionDetailsGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetVersionDetailsProjectionRoot projectionRoot = new GetVersionDetailsProjectionRoot<>()
                .compatibleVersions()
                .latestVersion();

        InfraVersionDetails response = litmusClient.getVersionDetails(query, projectionRoot);
        assertThat(response).isInstanceOf(InfraVersionDetails.class);
    }

    @Test
    void generateSSHKey() throws LitmusApiException, IOException {
        GenerateSSHKeyGraphQLQuery query = new GenerateSSHKeyGraphQLQuery.Builder()
                .build();

        GenerateSSHKeyProjectionRoot projectionRoot = new GenerateSSHKeyProjectionRoot<>()
                .privateKey()
                .publicKey();

        SSHKey response = litmusClient.generateSSHKey(query, projectionRoot);
        assertThat(response).isInstanceOf(SSHKey.class);
    }

    // TODO: NOT TESTED
    @Test
    void getManifestWithInfraID() throws LitmusApiException, IOException {
        GetManifestWithInfraIDGraphQLQuery query = new GetManifestWithInfraIDGraphQLQuery.Builder()
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .infraID("a53f0ffc-d8df-4963-8701-c1b6de179531")
                .accessKey("test access key")
                .build();

        GetManifestWithInfraIDResponse response = litmusClient.getManifestWithInfraID(query);
        assertThat(response).isInstanceOf(GetManifestWithInfraIDResponse.class);
    }

    // subscription is not supported in current version
//    @Test
//    void getInfraEvents(){
//        GetInfraEventsGraphQLQuery query = new GetInfraEventsGraphQLQuery.Builder()
//                .projectID("d6f0b5cb-0088-4732-8c2f-4193419103de")
//                .build();
//
//        GetInfraEventsProjectionRoot projectionRoot = new GetInfraEventsProjectionRoot<>()
//                .eventID()
//                .description()
//                .eventName()
//                .eventType();
//
//        InfraEventResponse response = litmusClient.getInfraEvents(query, projectionRoot);
//        assertThat(response).isInstanceOf(InfraEventResponse.class);
//    }
//
//    @Test
//    void getKubeNamespace(){
//        GetKubeNamespaceGraphQLQuery query = new GetKubeNamespaceGraphQLQuery.Builder()
//                .request(KubeNamespaceRequest.newBuilder()
//                        .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
//                        .build())
//                .build();
//
//        GetKubeNamespaceProjectionRoot projectionRoot = new GetKubeNamespaceProjectionRoot<>()
//                .infraID()
//                .kubeNamespace().name().root();
//
//        KubeNamespaceResponse response = litmusClient.getKubeNamespace(query, projectionRoot);
//        assertThat(response).isInstanceOf(KubeNamespaceResponse.class);
//    }
//
//    @Test
//    void getKubeObject(){
//        GetKubeObjectGraphQLQuery query = new GetKubeObjectGraphQLQuery.Builder()
//                .request(KubeObjectRequest.newBuilder()
//                        .namespace("default")
//                        .objectType("kubeobject")
//                        .infraID("6c54cea0-16e1-4d7b-bf96-ece11c82a7e4")
//                        .kubeObjRequest(KubeGVRRequest.newBuilder()
//                                .group("apps")
//                                .resource("deployments")
//                                .version("v1")
//                                .build())
//                        .build())
//                .build();
//
//        GetKubeObjectProjectionRoot projectionRoot = new GetKubeObjectProjectionRoot<>()
//                .infraID()
//                .kubeObj().data().labels().root();
//
//        KubeObjectResponse response = litmusClient.getKubeObject(query, projectionRoot);
//        assertThat(response).isInstanceOf(KubeObjectResponse.class);
//    }
//
//    @Test
//    void getPodLog(){
//        GetPodLogGraphQLQuery query = new GetPodLogGraphQLQuery.Builder()
//                .request(PodLogRequest.newBuilder()
//                        .infraID("4357805a-c932-4f9d-a3c5-cc1e3b3693a4")
//                        .experimentRunID("2be2bcdf-0d1e-4b9a-9ec8-03068272e1bc")
//                        .podName("test-experiment-1737970826261-2717703891")
//                        .podNamespace("litmus")
//                        .podType("Pod")
//                        .build())
//                .build();
//
//        GetPodLogProjectionRoot projectionRoot = new GetPodLogProjectionRoot<>()
//                .log()
//                .experimentRunID();
//
//        PodLogResponse response = litmusClient.getPodLog(query, projectionRoot);
//        assertThat(response).isInstanceOf(PodLogResponse.class);
//    }
//
//    // is it work?
//    @Test
//    void infraConnect(){
//        InfraConnectGraphQLQuery query = new InfraConnectGraphQLQuery.Builder()
//                .request(InfraIdentity.newBuilder()
//                        .version("test")
//                        .accessKey("test")
//                        .infraID("a53f0ffc-d8df-4963-8701-c1b6de179531")
//                        .build())
//                .build();
//
//        InfraConnectProjectionRoot projectionRoot = new InfraConnectProjectionRoot<>()
//                .projectID()
//                .action().externalData().root();
//
//        InfraActionResponse response = litmusClient.infraConnect(query, projectionRoot);
//        assertThat(response).isInstanceOf(InfraActionResponse.class);
//    }
}
