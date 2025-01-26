import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.codegen.BaseSubProjectionNode;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
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

    private static final String HOST_URL = "http://127.0.0.1:3000";
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private final MockLitmusClient litmusClient = new MockLitmusClient(HOST_URL,TEST_TOKEN);

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
    public void getEnvironment() throws IOException, LitmusApiException {
        GetEnvironmentGraphQLQuery query = new GetEnvironmentGraphQLQuery.Builder()
                .queryName("getEnvironment")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .environmentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
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
    public void listEnvironments() throws IOException, LitmusApiException {
        ListEnvironmentsGraphQLQuery query = new ListEnvironmentsGraphQLQuery.Builder()
                .queryName("listEnvironments")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
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
    public void createEnvironment() {
        CreateEnvironmentGraphQLQuery query = new CreateEnvironmentGraphQLQuery.Builder()
                .queryName("createEnvironment")
                .projectID("3f397b8c-292f-4d4d-b5dd-447e3205acd1")
                .request(CreateEnvironmentRequest.newBuilder()
                        .environmentID("TEST-ENVIRONMENT-ID-")
                        .tags(List.of("tag1", "tag2"))
                        .description("Test environment description")
                        .name("TEST-ENVIRONMENT-NAME-")
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
    public void updateEnvironment() {
        UpdateEnvironmentGraphQLQuery query = new UpdateEnvironmentGraphQLQuery.Builder()
                .queryName("updateEnvironment")
                .projectID("3f397b8c-292f-4d4d-b5dd-447e3205acd1")
                .request(UpdateEnvironmentRequest.newBuilder()
                        .environmentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                        .name("updated environment")
                        .tags(List.of("tag1", "tag2"))
                        .type(EnvironmentType.NON_PROD)
                        .description("Updated environment description")
                        .build()
                )
                .build();

        UpdateEnvironmentResponse response = litmusClient.updateEnvironment(query);
        assertThat(response).isInstanceOf(UpdateEnvironmentResponse.class);
    }

    @Test
    public void deleteEnvironment() {
        DeleteEnvironmentGraphQLQuery query = new DeleteEnvironmentGraphQLQuery.Builder()
                .queryName("deleteEnvironment")
                .projectID("3f397b8c-292f-4d4d-b5dd-447e3205acd1")
                .environmentID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .build();

        DeleteEnvironmentResponse response = litmusClient.deleteEnvironment(query);
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
    public void getInfraStats(){
        GetInfraStatsGraphQLQuery query = new GetInfraStatsGraphQLQuery.Builder()
                .queryName("getInfraStats")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
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

    @Test
    public void getInfraManifest(){
        GetInfraManifestGraphQLQuery query = new GetInfraManifestGraphQLQuery.Builder()
                .queryName("getInfraManifest")
                .projectID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .infraID("50703e0e-18de-4cc4-80fb-0784c100bb07")
                .upgrade(true)
                .build();

        GetInfraManifestResponse response = litmusClient.getInfraManifest(query);
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
}
