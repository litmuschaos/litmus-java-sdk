import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.client.*;
import io.litmuschaos.generated.types.*;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Example {

    private static final String HOST_URL = "http://localhost:55678";
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ4OTEyNTE3MTQsInJvbGUiOiJhZG1pbiIsInVpZCI6Ijc2OTAwYzcxLTgwODUtNGY2Mi04NDIzLTU4MTljZDc5ZmMzNyIsInVzZXJuYW1lIjoiYWRtaW4ifQ.wnKjLhPZ9IvSDZDM_v85K124R_i-JZmPtnByMOZsAWguMthgYoJXzNBodGkjA6qHNag1t19oRuC1fq_E1AKTbA";

    private final LitmusClient litmusClient = new LitmusClient(HOST_URL,TEST_TOKEN);

    // authentication

    @Test
    public void getTokens() throws IOException, LitmusApiException {
        String userId = "5ee70855-c77e-4a8f-a0d7-715bb5846bdd";
        ListTokensResponse response = litmusClient.getTokens(userId);
        assertThat(response).isInstanceOf(ListTokensResponse.class);
    }

    @Test
    public void createToken() throws IOException, LitmusApiException {
        TokenCreateRequest request = TokenCreateRequest.builder()
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .name("token name")
                .daysUntilExpiration(36500)
                .build();
        TokenCreateResponse response = litmusClient.createToken(request);
        assertThat(response).isInstanceOf(TokenCreateResponse.class);
    }

    // It is not working in local environment
    @Test
    public void deleteToken() throws IOException, LitmusApiException {
        TokenDeleteRequest request = TokenDeleteRequest.builder()
                .token(TEST_TOKEN)
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();
        CommonResponse response = litmusClient.deleteToken(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void getUser() throws IOException, LitmusApiException {
        String userId = "5ee70855-c77e-4a8f-a0d7-715bb5846bdd";
        UserResponse response = litmusClient.getUser(userId);
        assertThat(response).isInstanceOf(UserResponse.class);
    }

    @Test
    public void getUsers() throws IOException, LitmusApiException {
        List<UserResponse> response = litmusClient.getUsers();
        System.out.println(response);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void updatePassword() throws IOException, LitmusApiException {
        PasswordUpdateRequest request = PasswordUpdateRequest.builder().
                username("test_username").
                oldPassword("old password").
                newPassword("new password").
                build();
        PasswordUpdateResponse response = litmusClient.updatePassword(request);
        assertThat(response).isInstanceOf(PasswordUpdateResponse.class);
    }

    @Test
    public void createUser() throws IOException, LitmusApiException {
        UserCreateRequest request = UserCreateRequest.builder()
                .username("test_username")
                .password("test password")
                .role("admin") // Role can be admin, user
                .email("test@litmus.com")
                .build();
        UserResponse response = litmusClient.createUser(request);
        assertThat(response).isInstanceOf(UserResponse.class);

    }

    // only admin can call reset password
    @Test
    public void resetPassword() throws IOException, LitmusApiException {
        PasswordResetRequest request = PasswordResetRequest.builder()
                .username("test_username")
                .newPassword("new password")
                .build();
        CommonResponse response = litmusClient.resetPassword(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void updateUserDetails() throws IOException, LitmusApiException {
        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .name("updated name")
                .email("updated@litmus.com")
                .build();
        CommonResponse response = litmusClient.updateUserDetails(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void updateUserState() throws IOException, LitmusApiException {
        UserStateUpdateRequest request = UserStateUpdateRequest.builder()
                .username("test_username")
                .isDeactivate(true)
                .build();
        CommonResponse response = litmusClient.updateUserState(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void capabilities() throws IOException, LitmusApiException {
        CapabilityResponse response = litmusClient.capabilities();
        assertThat(response).isInstanceOf(CapabilityResponse.class);
    }

    @Test
    public void listProjects() throws IOException, LitmusApiException {
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
    public void createProject() throws IOException, LitmusApiException {
        CreateProjectRequest request = CreateProjectRequest.builder()
                .projectName("test projectName")
                .description("test description")
                .tags(List.of("tag1", "tag2"))
                .build();
        ProjectResponse response = litmusClient.createProject(request);
        assertThat(response).isInstanceOf(ProjectResponse.class);
    }

    @Test
    public void updateProjectName() throws IOException, LitmusApiException {
        ProjectNameRequest request = ProjectNameRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .projectName("test projectName")
                .build();
        CommonResponse response = litmusClient.updateProjectName(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void getProject() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        ProjectResponse response = litmusClient.getProject(projectID);
        assertThat(response).isInstanceOf(ProjectResponse.class);
    }

    @Test
    public void getOwnerProjects() throws IOException, LitmusApiException {
        List<ProjectResponse> response = litmusClient.getOwnerProjects();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void leaveProject() throws IOException, LitmusApiException {
        LeaveProjectRequest request = LeaveProjectRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();
        CommonResponse response = litmusClient.leaveProject(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void getProjectRole() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        ProjectRoleResponse response = litmusClient.getProjectRole(projectID);
        assertThat(response).isInstanceOf(ProjectRoleResponse.class);
    }

    @Test
    public void getUserWithProject() throws IOException, LitmusApiException {
        String username = "test_username";
        UserWithProjectResponse response = litmusClient.getUserWithProject(username);
        assertThat(response).isInstanceOf(UserWithProjectResponse.class);
    }

    @Test
    public void getProjectsStats() throws IOException, LitmusApiException {
        List<ProjectsStatsResponse> response = litmusClient.getProjectsStats();
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void getProjectMembers() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        String status = "accepted"; // Status can be accepted, not_accepted
        List<ProjectMemberResponse> response = litmusClient.getProjectMembers(projectID, status);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void getProjectOwners() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        List<ProjectMemberResponse> response = litmusClient.getProjectOwners(projectID);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void deleteProject() throws IOException, LitmusApiException {
        String projectID = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        CommonResponse response = litmusClient.deleteProject(projectID);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void sendInvitation() throws IOException, LitmusApiException {
        SendInvitationRequest request = SendInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .role("Executor") // Role can be Owner, Executor, Viewer
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        SendInvitationResponse response = litmusClient.sendInvitation(request);
        assertThat(response).isInstanceOf(SendInvitationResponse.class);
    }

    @Test
    public void acceptInvitation() throws IOException, LitmusApiException {
        AcceptInvitationRequest request = AcceptInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.acceptInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void declineInvitation() throws IOException, LitmusApiException {
        DeclineInvitationRequest request = DeclineInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.declineInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void removeInvitation() throws IOException, LitmusApiException {
        RemoveInvitationRequest request = RemoveInvitationRequest.builder()
                .projectId("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userId("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .build();

        CommonResponse response = litmusClient.removeInvitation(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void listInvitation() throws IOException, LitmusApiException {
        String status = "Accepted"; // Status can be Pending, Accepted, Declined, Exited
        List<ListInvitationResponse> response = litmusClient.listInvitation(status);
        assertThat(response).isInstanceOf(List.class);
    }

    // List of users that can be invited
    @Test
    public void inviteUsers() throws IOException, LitmusApiException {
        String projectId = "50703e0e-18de-4cc4-80fb-0784c100bb07";
        List<InviteUsersResponse> response = litmusClient.inviteUsers(projectId);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void updateMemberRole() throws IOException, LitmusApiException {
        UpdateMemberRoleRequest request = UpdateMemberRoleRequest.builder()
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .userID("5ee70855-c77e-4a8f-a0d7-715bb5846bdd")
                .role("Executor") // Role can be Owner, Executor, Viewer
                .build();

        CommonResponse response = litmusClient.updateMemberRole(request);
        assertThat(response).isInstanceOf(CommonResponse.class);
    }

    @Test
    public void status() throws IOException, LitmusApiException {
        StatusResponse response = litmusClient.status();
        assertThat(response).isInstanceOf(StatusResponse.class);
    }

    @Test
    public void readiness() throws IOException, LitmusApiException {
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
    public void getEnvironment() throws IOException, LitmusApiException { // check
        GetEnvironmentGraphQLQuery query = new GetEnvironmentGraphQLQuery.Builder()
                .queryName("getEnvironment")
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
    public void listEnvironments() throws IOException, LitmusApiException { // check
        ListEnvironmentsGraphQLQuery query = new ListEnvironmentsGraphQLQuery.Builder()
                .queryName("listEnvironments")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(new ListEnvironmentRequest.Builder()
                //      .environmentIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                //      .filter(EnvironmentFilterInput.newBuilder()
                //                .name("test")
                //                .description("test")
                //                .tags(List.of("tag1", "tag2"))
                //                .type(EnvironmentType.PROD)
                //                .build())
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
        System.out.println(response);
        assertThat(response).isInstanceOf(ListEnvironmentResponse.class);
    }

    @Test
    public void createEnvironment() { // check
        CreateEnvironmentGraphQLQuery query = new CreateEnvironmentGraphQLQuery.Builder()
                .queryName("createEnvironment")
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
        System.out.println(response);
        assertThat(response).isInstanceOf(Environment.class);
    }

    @Test
    public void updateEnvironment() { // check
        UpdateEnvironmentGraphQLQuery query = new UpdateEnvironmentGraphQLQuery.Builder()
                .queryName("updateEnvironment")
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
        System.out.println(response);
        assertThat(response).isInstanceOf(UpdateEnvironmentResponse.class);
    }

    @Test
    public void deleteEnvironment() { // check
        DeleteEnvironmentGraphQLQuery query = new DeleteEnvironmentGraphQLQuery.Builder()
                .queryName("deleteEnvironment")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .environmentID("test-environments")
                .build();

        DeleteEnvironmentResponse response = litmusClient.deleteEnvironment(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(DeleteEnvironmentResponse.class);
    }

    @Test
    public void getInfra() {
        GetInfraGraphQLQuery query = new GetInfraGraphQLQuery.Builder()
                .queryName("getInfra")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();
        GetInfraProjectionRoot projectionRoot = new GetInfraProjectionRoot<>()
                .infraSaExists()
                .infraNsExists()
                .infraNamespace()
                .description()
                .environmentID()
                .infraID()
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
                .updatedBy().userID().email().username().root()
                .updateStatus().root();

        Infra response = litmusClient.getInfra(query, projectionRoot);
        assertThat(response).isInstanceOf(Infra.class);
    }

    @Test
    public void listInfras() {

        ListInfrasGraphQLQuery query = new ListInfrasGraphQLQuery.Builder()
                .queryName("listInfras")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(new ListInfraRequest.Builder()
                        .infraIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                        .environmentIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
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
                .updateStatus().parent()
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
                .updatedBy().userID().email().username().parent()
                .updateStatus().root()
                .totalNoOfInfras();

        ListInfraResponse response = litmusClient.listInfras(query, projectionRoot);
        assertThat(response).isInstanceOf(ListInfraResponse.class);
    }

    @Test
    public void getInfraDetails(){
        GetInfraDetailsGraphQLQuery query = new GetInfraDetailsGraphQLQuery.Builder()
                .queryName("getInfraDetails")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
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
                .updatedBy().userID().email().username().root()
                .updateStatus().root();

        Infra response = litmusClient.getInfraDetails(query, projectionRoot);
        assertThat(response).isInstanceOf(Infra.class);
    }

    @Test
    public void getInfraStats(){ // check
        GetInfraStatsGraphQLQuery query = new GetInfraStatsGraphQLQuery.Builder()
                .queryName("getInfraStats")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetInfraStatsProjectionRoot projectionRoot = new GetInfraStatsProjectionRoot<>()
                .totalActiveInfrastructure()
                .totalConfirmedInfrastructure()
                .totalInactiveInfrastructures()
                .totalInfrastructures()
                .totalNonConfirmedInfrastructures();


        GetInfraStatsResponse response = litmusClient.getInfraStats(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(GetInfraStatsResponse.class);
    }

    @Test
    public void getInfraManifest(){
        GetInfraManifestGraphQLQuery query = new GetInfraManifestGraphQLQuery.Builder()
                .queryName("getInfraManifest")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .infraID("4357805a-c932-4f9d-a3c5-cc1e3b3693a4")
                .upgrade(false)
                .build();

        GetInfraManifestResponse response = litmusClient.getInfraManifest(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(GetInfraManifestResponse.class);

    }

    @Test
    public void confirmInfraRegistration(){
        ConfirmInfraRegistrationGraphQLQuery query = new ConfirmInfraRegistrationGraphQLQuery.Builder()
                .queryName("confirmInfraRegistration")
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
    public void deleteInfra(){
        DeleteInfraGraphQLQuery query = new DeleteInfraGraphQLQuery.Builder()
                .queryName("deleteInfra")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        DeleteInfraResponse response = litmusClient.deleteInfra(query);
        assertThat(response).isInstanceOf(DeleteInfraResponse.class);
    }

    @Test
    public void registerInfra(){
        RegisterInfraGraphQLQuery query = new RegisterInfraGraphQLQuery.Builder()
                .queryName("registerInfra")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(RegisterInfraRequest.newBuilder()
                        .description("test description")
                        .environmentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .infraNamespace("test namespace")
                        .infraNsExists(true)
                        .infraSaExists(true)
                        .infraScope(INFRA_SCOPE.cluster.name())
                        .infrastructureType(InfrastructureType.Kubernetes)
                        .name("test name")
                        .nodeSelector("test node selector")
                        .platformName("test platform name")
                        .serviceAccount("test service account")
                        .skipSsl(true)
                        .tags(List.of("tag1", "tag2"))
                        .tolerations(List.of(Toleration.newBuilder().value("test value").key("test key").effect("test effect").build()))
                        .build()
                )
                .build();

        RegisterInfraProjectionRoot projectionRoot = new RegisterInfraProjectionRoot<>()
                .infraID()
                .name()
                .manifest()
                .token();

        RegisterInfraResponse response = litmusClient.registerInfra(query, projectionRoot);
        assertThat(response).isInstanceOf(RegisterInfraResponse.class);
    }

    @Test
    public void listChaosHub(){
        ListChaosHubGraphQLQuery query = new ListChaosHubGraphQLQuery.Builder()
                .queryName("listChaosHub")
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
                .isAvailable()
                .isDefault()
                .isPrivate();

        List<ChaosHubStatus> response = litmusClient.listChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void getChaosHub(){
        GetChaosHubGraphQLQuery query = new GetChaosHubGraphQLQuery.Builder()
                .queryName("getChaosHub")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .chaosHubID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        GetChaosHubProjectionRoot projectionRoot = new GetChaosHubProjectionRoot<>()
                .name()
                .description()
                .id()
                .isAvailable()
                .isDefault()
                .isPrivate();

        ChaosHubStatus response = litmusClient.getChaosHub(query, projectionRoot);
        assertThat(response).isInstanceOf(ChaosHubStatus.class);
    }

    @Test
    public void getChaosHubStats(){
        GetChaosHubStatsGraphQLQuery query = new GetChaosHubStatsGraphQLQuery.Builder()
                .queryName("getChaosHubStats")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        GetChaosHubStatsProjectionRoot projectionRoot = new GetChaosHubStatsProjectionRoot<>()
                .totalChaosHubs();

        GetChaosHubStatsResponse response = litmusClient.getChaosHubStats(query, projectionRoot);
        assertThat(response).isInstanceOf(GetChaosHubStatsResponse.class);
    }

    @Test
    public void addChaosHub(){
        AddChaosHubGraphQLQuery query = new AddChaosHubGraphQLQuery.Builder()
                .queryName("addChaosHub")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(CreateChaosHubRequest.newBuilder()
                        .remoteHub("test remote hub")
                        .authType(AuthType.BASIC)
                        .isPrivate(true)
                        .tags(List.of("tag1", "tag2"))
                        .repoBranch("test repo branch")
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

    @Test
    public void addRemoteChaosHub(){
        AddRemoteChaosHubGraphQLQuery query = new AddRemoteChaosHubGraphQLQuery.Builder()
                .queryName("addRemoteChaosHub")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
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
    public void deleteChaosHub(){
        DeleteChaosHubGraphQLQuery query = new DeleteChaosHubGraphQLQuery.Builder()
                .queryName("deleteChaosHub")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .hubID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        DeleteChaosHubResponse response = litmusClient.deleteChaosHub(query);
        assertThat(response).isInstanceOf(DeleteChaosHubResponse.class);
    }

    @Test
    public void saveChaosHub(){
        SaveChaosHubGraphQLQuery query = new SaveChaosHubGraphQLQuery.Builder()
                .queryName("saveChaosHub")
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
    public void syncChaosHub(){
        SyncChaosHubGraphQLQuery query = new SyncChaosHubGraphQLQuery.Builder()
                .queryName("syncChaosHub")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .id("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        SyncChaosHubResponse response = litmusClient.syncChaosHub(query);
        assertThat(response).isInstanceOf(SyncChaosHubResponse.class);
    }

    @Test
    public void updateChaosHub(){
        UpdateChaosHubGraphQLQuery query = new UpdateChaosHubGraphQLQuery.Builder()
                .queryName("updateChaosHub")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(UpdateChaosHubRequest.newBuilder()
                        .id("50703e0e-18de-4cc4-80fb-0784c100bb07")
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
    public void getExperiment(){
        GetExperimentGraphQLQuery query = new GetExperimentGraphQLQuery.Builder()
                .queryName("getExperiment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        GetExperimentProjectionRoot projectionRoot = new GetExperimentProjectionRoot<>()
                .averageResiliencyScore()
                .experimentDetails().parent()
                .experimentDetails()
                .createdAt()
                .projectID()
                .root();


        GetExperimentResponse response = litmusClient.getExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(GetExperimentResponse.class);
    }

    @Test
    public void listExperiment(){
        ListExperimentGraphQLQuery query = new ListExperimentGraphQLQuery.Builder()
                .queryName("listExperiment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(new ListExperimentRequest.Builder()
                        .experimentIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
                        .filter(ExperimentFilterInput.newBuilder()
                                .experimentName("test")
                                .dateRange(DateRange.newBuilder()
                                        .startDate("2021-01-01")
                                        .endDate("2021-12-31")
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
    public void getExperimentStats(){
        GetExperimentStatsGraphQLQuery query = new GetExperimentStatsGraphQLQuery.Builder()
                .queryName("getExperimentStats")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        GetExperimentStatsProjectionRoot projectionRoot = new GetExperimentStatsProjectionRoot<>()
                .totalExperiments()
                .totalExpCategorizedByResiliencyScore()
                .root();

        GetExperimentStatsResponse response = litmusClient.getExperimentStats(query, projectionRoot);
        assertThat(response).isInstanceOf(GetExperimentStatsResponse.class);
    }

    @Test
    public void getPredefinedExperiment(){
        GetPredefinedExperimentGraphQLQuery query = new GetPredefinedExperimentGraphQLQuery.Builder()
                .queryName("getPredefinedExperiment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentName(List.of("test"))
                .hubID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        GetPredefinedExperimentProjectionRoot projectionRoot = new GetPredefinedExperimentProjectionRoot<>()
                .experimentCSV()
                .experimentName()
                .experimentManifest();

        List<PredefinedExperimentList> response = litmusClient.getPredefinedExperiment(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void listPredefinedExperiments(){
        ListPredefinedExperimentsGraphQLQuery query = new ListPredefinedExperimentsGraphQLQuery.Builder()
                .queryName("listPredefinedExperiments")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .hubID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        ListPredefinedExperimentsProjectionRoot projectionRoot = new ListPredefinedExperimentsProjectionRoot<>()
                .experimentCSV()
                .experimentName()
                .experimentManifest();

        List<PredefinedExperimentList> response = litmusClient.listPredefinedExperiments(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void runChaosExperiment(){ // check
        RunChaosExperimentGraphQLQuery query = new RunChaosExperimentGraphQLQuery.Builder()
                .queryName("runChaosExperiment")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .experimentID("1af067d5-fec7-4117-92df-036f5a571372")
                .build();

        RunChaosExperimentProjectionRoot projectionRoot = new RunChaosExperimentProjectionRoot<>()
                .notifyID();

        RunChaosExperimentResponse response = litmusClient.runChaosExperiment(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(RunChaosExperimentResponse.class);
    }

    @Test
    public void saveChaosExperiment(){
        SaveChaosExperimentGraphQLQuery query = new SaveChaosExperimentGraphQLQuery.Builder()
                .queryName("saveChaosExperiment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(SaveChaosExperimentRequest.newBuilder()
                        .description("test description")
                        .id("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .manifest("test manifest")
                        .tags(List.of("tag1", "tag2"))
                        .type(ExperimentType.Experiment)
                        .name("test name")
                        .build())
                .build();


        SaveChaosExperimentResponse response = litmusClient.saveChaosExperiment(query);
        assertThat(response).isInstanceOf(SaveChaosExperimentResponse.class);
    }

    @Test
    public void updateChaosExperiment(){
        UpdateChaosExperimentGraphQLQuery query = new UpdateChaosExperimentGraphQLQuery.Builder()
                .queryName("updateChaosExperiment")
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

    @Test
    public void createChaosExperiment(){
        CreateChaosExperimentGraphQLQuery query = new CreateChaosExperimentGraphQLQuery.Builder()
                .queryName("createChaosExperiment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(ChaosExperimentRequest.newBuilder()
                        .tags(List.of("tag1", "tag2"))
                        .runExperiment(true)
                        .experimentDescription("test description")
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .experimentManifest("test manifest")
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
    public void deleteChaosExperiment(){
        DeleteChaosExperimentGraphQLQuery query = new DeleteChaosExperimentGraphQLQuery.Builder()
                .queryName("deleteChaosExperiment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .experimentRunID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        DeleteChaosExperimentResponse response = litmusClient.deleteChaosExperiment(query);
        assertThat(response).isInstanceOf(DeleteChaosExperimentResponse.class);
    }

    @Test
    public void updateCronExperimentState(){
        UpdateCronExperimentStateGraphQLQuery query = new UpdateCronExperimentStateGraphQLQuery.Builder()
                .queryName("updateCronExperimentState")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .disable(false)
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        UpdateCronExperimentStateResponse response = litmusClient.updateCronExperimentState(query);
        assertThat(response).isInstanceOf(UpdateCronExperimentStateResponse.class);
    }

    // Chaos Experiment Run
    @Test
    public void getExperimentRun(){ // check
        GetExperimentRunGraphQLQuery query = new GetExperimentRunGraphQLQuery.Builder()
                .queryName("getExperimentRun")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .notifyID("7502028c-1103-4c54-8ac3-e5b5ec9b49f5")
                // .experimentRunID("43bc2abd-bb9f-431a-95d9-458b380b5e1a")
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
        System.out.println(response);
        assertThat(response).isInstanceOf(ExperimentRun.class);

    }

    @Test
    public void getExperimentRunStats(){ // check
        GetExperimentRunStatsGraphQLQuery query = new GetExperimentRunStatsGraphQLQuery.Builder()
                .queryName("getExperimentRunStats")
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
        System.out.println(response);
        assertThat(response).isInstanceOf(GetExperimentRunStatsResponse.class);
    }

    @Test
    public void listExperimentRun(){ // check
        ListExperimentRunGraphQLQuery query = new ListExperimentRunGraphQLQuery.Builder()
                .queryName("listExperimentRun")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(new ListExperimentRunRequest.Builder()
//                        .experimentRunIDs(List.of("50703e0e-18de-4cc4-80fb-0784c100bb07"))
//                        .filter(ExperimentRunFilterInput.newBuilder()
//                                .experimentRunID("50703e0e-18de-4cc4-80fb-0784c100bb07")
//                                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
//                                .experimentName("test")
//                                .experimentRunStatus(List.of(ExperimentRunStatus.Running.name()))
//                                .dateRange(DateRange.newBuilder()
//                                        .startDate("2021-01-01")
//                                        .endDate("2021-12-31")
//                                        .build())
//                                .build()
//                        )
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
        System.out.println(response);
        assertThat(response).isInstanceOf(ListExperimentRunResponse.class);
    }

    @Test
    public void chaosExperimentRun(){
        ChaosExperimentRunGraphQLQuery query = new ChaosExperimentRunGraphQLQuery.Builder()
                .queryName("chaosExperimentRun")
                .request(ExperimentRunRequest.newBuilder()
                        .experimentID("1af067d5-fec7-4117-92df-036f5a571372")
                        .experimentRunID("43bc2abd-bb9f-431a-95d9-458b380b5e1a")
                        .experimentName("test-experiment")
                        .completed(false)
                        .build())
                .build();

        ChaosExperimentRunResponse response = litmusClient.chaosExperimentRun(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(ChaosExperimentRunResponse.class);
    }

    @Test
    public void stopExperimentRuns(){ // check
        StopExperimentRunsGraphQLQuery query = new StopExperimentRunsGraphQLQuery.Builder()
                .queryName("stopExperimentRuns")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .experimentID("1af067d5-fec7-4117-92df-036f5a571372")
         //       .experimentRunID("b6f3115b-8f96-434d-ac02-f815d64832b7") optional
         //       .notifyID("b6f3115b-8f96-434d-ac02-f815d64832b7") optional
                .build();

        StopExperimentRunsResponse response = litmusClient.stopExperimentRuns(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(StopExperimentRunsResponse.class);
    }

    // GitOps
    @Test
    public void getGitOpsDetails(){ // check
        GetGitOpsDetailsGraphQLQuery query = new GetGitOpsDetailsGraphQLQuery.Builder()
                .queryName("getGitOpsDetails")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetGitOpsDetailsProjectionRoot projectionRoot = new GetGitOpsDetailsProjectionRoot<>()
                .projectID()
                .branch()
                .repoURL();

        GitConfigResponse response = litmusClient.getGitOpsDetails(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(GitConfigResponse.class);
    }

    @Test
    public void disableGitOps(){ // check
        DisableGitOpsGraphQLQuery query = new DisableGitOpsGraphQLQuery.Builder()
                .queryName("disableGitOps")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        DisableGitOpsResponse response = litmusClient.disableGitOps(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(DisableGitOpsResponse.class);
    }

    @Test
    public void enableGitOps(){ // check
        EnableGitOpsGraphQLQuery query = new EnableGitOpsGraphQLQuery.Builder()
                .queryName("enableGitOps")
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
        System.out.println(response);
        assertThat(response).isInstanceOf(EnableGitOpsResponse.class);
    }

    @Test
    public void gitopsNotifier(){
        GitopsNotifierGraphQLQuery query = new GitopsNotifierGraphQLQuery.Builder()
                .queryName("gitopsNotifier")
                .experimentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .clusterInfo(InfraIdentity.newBuilder()
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .accessKey("test access key")
                        .version("test version")
                        .build())
                .build();

        GitOpsNotifierResponse response = litmusClient.gitopsNotifier(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(GitOpsNotifierResponse.class);
    }

    @Test
    public void updateGitOps(){
        UpdateGitOpsGraphQLQuery query = new UpdateGitOpsGraphQLQuery.Builder()
                .queryName("updateGitOps")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .configurations(GitConfig.newBuilder()
                        .authType(AuthType.BASIC)
                        .branch("test")
                        .password("test")
                        .repoURL("test")
                        .sshPrivateKey("test")
                        .build())
                .build();

        UpdateGitOpsResponse response = litmusClient.updateGitOps(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(UpdateGitOpsResponse.class);
    }

    // Image Registry
    @Test
    public void getImageRegistry(){ // check
        GetImageRegistryGraphQLQuery query = new GetImageRegistryGraphQLQuery.Builder()
                .queryName("getImageRegistry")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetImageRegistryProjectionRoot projectionRoot = new GetImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID()
                .isRemoved();

        ImageRegistryResponse response = litmusClient.getImageRegistry(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(ImageRegistryResponse.class);
    }

    @Test
    public void listImageRegistry(){
        ListImageRegistryGraphQLQuery query = new ListImageRegistryGraphQLQuery.Builder()
                .queryName("listImageRegistry")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        ListImageRegistryProjectionRoot projectionRoot = new ListImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID();

        List<ImageRegistryResponse> response = litmusClient.listImageRegistry(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void createImageRegistry(){
        CreateImageRegistryGraphQLQuery query = new CreateImageRegistryGraphQLQuery.Builder()
                .queryName("createImageRegistry")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .imageRegistryInfo(ImageRegistryInput.newBuilder()
                        .enableRegistry(true)
                        .imageRegistryName("test")
                        .imageRegistryType("docker")
                        .imageRepoName("test")
                        .isDefault(true)
                        .build())
                .build();

        CreateImageRegistryProjectionRoot projectionRoot = new CreateImageRegistryProjectionRoot<>()
                .imageRegistryID()
                .projectID();

        ImageRegistryResponse response = litmusClient.createImageRegistry(query, projectionRoot);
        assertThat(response).isInstanceOf(ImageRegistryResponse.class);
    }

    @Test
    public void deleteImageRegistry(){
        DeleteImageRegistryGraphQLQuery query = new DeleteImageRegistryGraphQLQuery.Builder()
                .queryName("deleteImageRegistry")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .imageRegistryID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        DeleteImageRegistryResponse response = litmusClient.deleteImageRegistry(query);
        assertThat(response).isInstanceOf(DeleteImageRegistryResponse.class);
    }

    @Test
    public void updateImageRegistry(){
        UpdateImageRegistryGraphQLQuery query = new UpdateImageRegistryGraphQLQuery.Builder()
                .queryName("updateImageRegistry")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .imageRegistryID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .imageRegistryInfo(ImageRegistryInput.newBuilder()
                        .enableRegistry(true)
                        .imageRegistryName("test")
                        .imageRegistryType("docker")
                        .imageRepoName("test")
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
    public void listProbes(){ // check
        ListProbesGraphQLQuery query = new ListProbesGraphQLQuery.Builder()
                .queryName("listProbes")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .infrastructureType(InfrastructureType.Kubernetes) // type is Kubernetes
                // .probeNames(List.of("test"))
                .filter(ProbeFilterInput.newBuilder()
                        .dateRange(DateRange.newBuilder()
                                .startDate("1737039599000") // timestamp
                                .endDate("1734361200000") // timestamp
                                .build())
                        .type(List.of(ProbeType.cmdProbe)) // httpProbe, cmdProbe, promProbe, k8sProbe
                //        .name("test")
                        .build())
                .build();

        ListProbesProjectionRoot projectionRoot = new ListProbesProjectionRoot<>()
                .projectID()
                .name()
                .tags()
                .description();

        List<Probe> response = litmusClient.listProbes(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(List.class);
    }

    @Test
    public void getProbe(){ // check
        GetProbeGraphQLQuery query = new GetProbeGraphQLQuery.Builder()
                .queryName("getProbe")
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
        System.out.println(response);
        assertThat(response).isInstanceOf(Probe.class);
    }

    @Test
    public void validateUniqueProbe(){ // check
        ValidateUniqueProbeGraphQLQuery query = new ValidateUniqueProbeGraphQLQuery.Builder()
                .queryName("validateUniqueProbe")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .probeName("test-probe")
                .build();

        ValidateUniqueProbeResponse response = litmusClient.validateUniqueProbe(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(ValidateUniqueProbeResponse.class);
    }

    @Test
    public void getProbeReference(){ // we need to long time to graphql for updatedAt timestamp field
        GetProbeReferenceGraphQLQuery query = new GetProbeReferenceGraphQLQuery.Builder()
                .queryName("getProbeReference")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .probeName("test-probe")
                .build();

        GetProbeReferenceProjectionRoot projectionRoot = new GetProbeReferenceProjectionRoot<>()
                .name()
                .recentExecutions()
                .faultName()
                .mode().parent()
                .executionHistory()
                .executedByExperiment().experimentID().updatedAt().experimentName().updatedBy().username().email().parent().parent()
                .status().verdict().root()
                .totalRuns();

        GetProbeReferenceResponse response = litmusClient.getProbeReference(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(GetProbeReferenceResponse.class);
    }

    @Test
    public void getProbeYAML(){ // check
        GetProbeYAMLGraphQLQuery query = new GetProbeYAMLGraphQLQuery.Builder()
                .queryName("getProbeYAML")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .request(GetProbeYAMLRequest.newBuilder()
                        .probeName("test-probe")
                        .mode(Mode.EOT) // SOT, EOT, Edge, Continuous, OnChaos
                        .build())
                .build();

        GetProbeYAMLResponse response = litmusClient.getProbeYAML(query);
        System.out.println(response);
        assertThat(response).isInstanceOf(GetProbeYAMLResponse.class);
    }

    @Test
    public void getProbesInExperimentRun(){
        GetProbesInExperimentRunGraphQLQuery query = new GetProbesInExperimentRunGraphQLQuery.Builder()
                .queryName("getProbesInExperimentRun")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .experimentRunID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .faultName("test")
                .build();

        GetProbesInExperimentRunProjectionRoot projectionRoot = new GetProbesInExperimentRunProjectionRoot<>()
                .probe().projectID().description().root()
                .mode().root();

        GetProbesInExperimentRunResponse response = litmusClient.getProbesInExperimentRun(query, projectionRoot);
        assertThat(response).isInstanceOf(GetProbesInExperimentRunResponse.class);
    }

    @Test
    public void addProbe(){
        AddProbeGraphQLQuery query = new AddProbeGraphQLQuery.Builder()
                .queryName("addProbe")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(ProbeRequest.newBuilder()
                        .description("test description")
                        .infrastructureType(InfrastructureType.Kubernetes)
                        .name("test name")
                        .tags(List.of("tag1", "tag2"))
                        .kubernetesCMDProperties(KubernetesCMDProbeRequest.newBuilder()
                                .attempt(1)
                                .comparator(ComparatorInput.newBuilder().criteria("test").build())
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
    public void deleteProbe(){
        DeleteProbeGraphQLQuery query = new DeleteProbeGraphQLQuery.Builder()
                .queryName("deleteProbe")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .probeName("test")
                .build();

        DeleteProbeResponse response = litmusClient.deleteProbe(query);
        assertThat(response).isInstanceOf(DeleteProbeResponse.class);
    }

    @Test
    public void updateProbe(){
        UpdateProbeGraphQLQuery query = new UpdateProbeGraphQLQuery.Builder()
                .queryName("updateProbe")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(ProbeRequest.newBuilder()
                        .description("test description")
                        .infrastructureType(InfrastructureType.Kubernetes)
                        .name("test name")
                        .tags(List.of("tag1", "tag2"))
                        .kubernetesCMDProperties(KubernetesCMDProbeRequest.newBuilder()
                                .attempt(1)
                                .comparator(ComparatorInput.newBuilder().criteria("test").build())
                                .build())
                        .build())
                .build();

        UpdateProbeResponse response = litmusClient.updateProbe(query);
        assertThat(response).isInstanceOf(Probe.class);
    }

    // chaos fault
    @Test
    public void getChaosFault(){
        GetChaosFaultGraphQLQuery query = new GetChaosFaultGraphQLQuery.Builder()
                .queryName("getChaosFault")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .request(ExperimentRequest.newBuilder()
                        .category("test")
                        .experimentName("test")
                        .hubID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .build())
                .build();

        GetChaosFaultProjectionRoot projectionRoot = new GetChaosFaultProjectionRoot<>()
                .csv()
                .fault()
                .engine();

        FaultDetails response = litmusClient.getChaosFault(query, projectionRoot);
        assertThat(response).isInstanceOf(Chart.class);
    }

    @Test
    public void listChaosFaults(){
        ListChaosFaultsGraphQLQuery query = new ListChaosFaultsGraphQLQuery.Builder()
                .queryName("listChaosFaults")
                .hubID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        ListChaosFaultsProjectionRoot projectionRoot = new ListChaosFaultsProjectionRoot<>()
                .apiVersion()
                .kind()
                .metadata().name().annotations().root()
                .spec().categoryDescription().root();

        List<Chart> response = litmusClient.listChaosFaults(query, projectionRoot);
        assertThat(response).isInstanceOf(List.class);
    }

    // others
    @Test
    public void getServerVersion(){
        GetServerVersionGraphQLQuery query = new GetServerVersionGraphQLQuery.Builder()
                .queryName("getServerVersion")
                .build();

        GetServerVersionProjectionRoot projectionRoot = new GetServerVersionProjectionRoot<>()
                .key()
                .value();

        ServerVersionResponse response = litmusClient.getServerVersion(query, projectionRoot);
        assertThat(response).isInstanceOf(ServerVersionResponse.class);
    }

    @Test
    public void getVersionDetails(){ // check
        GetVersionDetailsGraphQLQuery query = new GetVersionDetailsGraphQLQuery.Builder()
                .queryName("getVersionDetails")
                .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
                .build();

        GetVersionDetailsProjectionRoot projectionRoot = new GetVersionDetailsProjectionRoot<>()
                .compatibleVersions()
                .latestVersion();

        InfraVersionDetails response = litmusClient.getVersionDetails(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(InfraVersionDetails.class);
    }

    @Test
    public void generateSSHKey(){ // check
        GenerateSSHKeyGraphQLQuery query = new GenerateSSHKeyGraphQLQuery.Builder()
                .queryName("generateSSHKey")
                .build();

        GenerateSSHKeyProjectionRoot projectionRoot = new GenerateSSHKeyProjectionRoot<>()
                .privateKey()
                .publicKey();

        SSHKey response = litmusClient.generateSSHKey(query, projectionRoot);
        assertThat(response).isInstanceOf(SSHKey.class);
    }

    @Test
    public void getManifestWithInfraID(){
        GetManifestWithInfraIDGraphQLQuery query = new GetManifestWithInfraIDGraphQLQuery.Builder()
                .queryName("getManifestWithInfraID")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .accessKey("test access key")
                .build();

        GetManifestWithInfraIDResponse response = litmusClient.getManifestWithInfraID(query);
        assertThat(response).isInstanceOf(GetManifestWithInfraIDResponse.class);
    }

    // subscription
    @Test
    public void getInfraEvents(){
        GetInfraEventsGraphQLQuery query = new GetInfraEventsGraphQLQuery.Builder()
                .queryName("getInfraEvents")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        GetInfraEventsProjectionRoot projectionRoot = new GetInfraEventsProjectionRoot<>()
                .eventID()
                .description()
                .eventName()
                .eventType();

        InfraEventResponse response = litmusClient.getInfraEvents(query, projectionRoot);
        assertThat(response).isInstanceOf(InfraEventResponse.class);
    }

    @Test
    public void getKubeNamespace(){
        GetKubeNamespaceGraphQLQuery query = new GetKubeNamespaceGraphQLQuery.Builder()
                .queryName("getKubeNamespace")
                .request(KubeNamespaceRequest.newBuilder()
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .build())
                .build();

        GetKubeNamespaceProjectionRoot projectionRoot = new GetKubeNamespaceProjectionRoot<>()
                .infraID()
                .kubeNamespace().name().root();

        KubeNamespaceResponse response = litmusClient.getKubeNamespace(query, projectionRoot);
        assertThat(response).isInstanceOf(KubeNamespaceResponse.class);
    }

    @Test
    public void getKubeObject(){
        GetKubeObjectGraphQLQuery query = new GetKubeObjectGraphQLQuery.Builder()
                .queryName("getKubeObject")
                .request(KubeObjectRequest.newBuilder()
                        .namespace("test")
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .objectType("test")
                        .workloads(List.of(Workload.newBuilder()
                                        .kind("test")
                                        .name("test")
                                        .namespace("test")
                                .build()))
                        .build())
                .build();

        GetKubeObjectProjectionRoot projectionRoot = new GetKubeObjectProjectionRoot<>()
                .infraID()
                .kubeObj().data().labels().root();

        KubeObjectResponse response = litmusClient.getKubeObject(query, projectionRoot);
        assertThat(response).isInstanceOf(KubeObjectResponse.class);
    }

    @Test
    public void getPodLog(){ // check
        GetPodLogGraphQLQuery query = new GetPodLogGraphQLQuery.Builder()
                .queryName("getPodLog")
                .request(PodLogRequest.newBuilder()
                        .infraID("4357805a-c932-4f9d-a3c5-cc1e3b3693a4")
                        .experimentRunID("2be2bcdf-0d1e-4b9a-9ec8-03068272e1bc")
                        .podName("test-experiment-1737970826261-2717703891")
                        .podNamespace("litmus")
                        .podType("Pod")
                        .build())
                .build();

        GetPodLogProjectionRoot projectionRoot = new GetPodLogProjectionRoot<>()
                .log()
                .experimentRunID();

        PodLogResponse response = litmusClient.getPodLog(query, projectionRoot);
        System.out.println(response);
        assertThat(response).isInstanceOf(PodLogResponse.class);
    }

    @Test
    public void infraConnect(){
        InfraConnectGraphQLQuery query = new InfraConnectGraphQLQuery.Builder()
                .queryName("infraConnect")
                .request(InfraIdentity.newBuilder()
                        .version("test")
                        .accessKey("test")
                        .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .build())
                .build();

        InfraConnectProjectionRoot projectionRoot = new InfraConnectProjectionRoot<>()
                .projectID()
                .action().externalData().root();

        InfraActionResponse response = litmusClient.infraConnect(query, projectionRoot);
        assertThat(response).isInstanceOf(InfraActionResponse.class);
    }
}
