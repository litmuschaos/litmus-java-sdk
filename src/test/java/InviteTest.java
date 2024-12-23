//import io.litmuschaos.LitmusClient;
//import io.litmuschaos.exception.LitmusApiException;
//import io.litmuschaos.request.*;
//import io.litmuschaos.response.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.security.SecureRandom;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class InviteTest {
//
//    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    private static final SecureRandom RANDOM = new SecureRandom();
//    private static final String HOST_URL = "http://localhost:3000";
//    private static final String TEST_TOKEN_STRING = "Bearer token";
//    private static final LitmusAuthToken TEST_TOKEN = new LitmusAuthToken(TEST_TOKEN_STRING);
//    private static final String oldPassword = "Litmus12345!";
//
//    private static final String TEST_USER_ROLE = "admin";
//    private static final String TEST_USER_EMAIL = "test@test.com";
//    private static final String TEST_USER_NAME = "userTestName";
//
//    private static LitmusClient litmusClient;
//    private static UserResponse user;
//    private static String projectId;
//    private static String userId;
//    private static String invitee;
//
//    @BeforeAll
//    public static void setupClient() throws IOException, LitmusApiException {
//        litmusClient = new LitmusClient(HOST_URL, TEST_TOKEN);
//        projectId = createProjectAndGetId();
//        String userTestName = generateRandomUsername();
//        user = createPasswordUpdatedTestUser(userTestName, oldPassword, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME, password);
//        userId = user.getUserID();
//        invitee = user.getUsername();
//    }
//
//    public static String generateRandomUsername() {
//        StringBuilder sb = new StringBuilder(6);
//        for (int i = 0; i < 6; i++) {
//            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
//        }
//        return sb.toString();
//    }
//
//    private static String generateUniqueProjectName(String baseName) {
//        return baseName + "-" + UUID.randomUUID().toString();
//    }
//
//    private static UserResponse createPasswordUpdatedTestUser(String username, String password, String role,
//                                                              String email, String name, String newPassword) throws LitmusApiException, IOException {
//
//        UserResponse user = createTestUser(username, password, role, email, name);
//        litmusClient.authenticate(LoginRequest.builder().username(username).password(password).build());
//        PasswordUpdateRequest passwordUpdateRequest = PasswordUpdateRequest.builder()
//                .username(username)
//                .oldPassword(password)
//                .newPassword(newPassword)
//                .build();
//        litmusClient.updatePassword(passwordUpdateRequest);
//        return litmusClient.getUser(user.getUserID());
//    }
//
//    private static UserResponse createTestUser(
//            String username, String password, String role, String email, String name
//    ) throws LitmusApiException, IOException {
//        UserCreateRequest request = UserCreateRequest.builder().
//                username(username)
//                .password(password)
//                .role(role)
//                .email(email)
//                .name(name).build();
//        return litmusClient.createUser(request);
//    }
//
//    private static String createProjectAndGetId() throws IOException, LitmusApiException {
//        String projectName = generateUniqueProjectName("Temporary Project");
//        String description = "This is a temporary project.";
//        List<String> tags = Arrays.asList("tag1", "tag2");
//
//        CreateProjectRequest request = CreateProjectRequest.builder()
//                .projectName(projectName)
//                .description(description)
//                .tags(tags)
//                .build();
//
//        ProjectResponse response = litmusClient.createProject(request);
//        return response.getProjectID();
//    }
//
//    public static SendInvitationResponse createInvite(String project, String user) throws IOException, LitmusApiException {
//        litmusClient.authenticate(LoginRequest.builder().username(username).password(password).build());
//        SendInvitationRequest request = SendInvitationRequest.builder()
//                .projectId(project)
//                .userId(user)
//                .role("Viewer")
//                .build();
//
//        return litmusClient.sendInvitation(request);
//    }
//
//    @Test
//    public void testCreateInvite() throws IOException, LitmusApiException {
//        SendInvitationResponse response = createInvite(projectId, userId);
//        assertThat(response.getUserID()).isEqualTo(userId);
//    }
//
//    @Test
//    public void testAcceptInvitation() throws IOException, LitmusApiException {
//        createInvite(projectId, userId);
//        AcceptInvitationRequest request = AcceptInvitationRequest.builder()
//                .projectId(projectId)
//                .userId(userId)
//                .build();
//
//        litmusClient.authenticate(LoginRequest.builder().username(invitee).password(password).build());
//        CommonResponse response = litmusClient.acceptInvitation(request);
//        assertThat(response.getMessage()).isEqualTo("Successful");
//    }
//
//    @Test
//    public void testDeclineInvitation() throws IOException, LitmusApiException {
//        createInvite(projectId, userId);
//
//        DeclineInvitationRequest request = DeclineInvitationRequest.builder()
//                .projectId(projectId)
//                .userId(userId)
//                .build();
//
//        litmusClient.authenticate(LoginRequest.builder().username(invitee).password(password).build());
//        CommonResponse response = litmusClient.declineInvitation(request);
//        assertThat(response.getMessage()).isEqualTo("Successful");
//    }
//
//    @Test
//    public void testRemoveInvitation() throws IOException, LitmusApiException {
//        String userTestName = generateRandomUsername();
//        user = createPasswordUpdatedTestUser(userTestName, oldPassword, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME, password);
//        userId = user.getUserID();
//        invitee = user.getUsername();
//        createInvite(projectId, userId);
//        RemoveInvitationRequest request = RemoveInvitationRequest.builder()
//                .projectId(projectId)
//                .userId(userId)
//                .build();
//
//        litmusClient.authenticate(LoginRequest.builder().username(username).password(password).build());
//        CommonResponse response = litmusClient.removeInvitation(request);
//        assertThat(response.getMessage()).isEqualTo("Successful");
//    }
//
//    @Test
//    public void testListInvitation() throws IOException, LitmusApiException {
//        String userTestName = generateRandomUsername();
//        user = createPasswordUpdatedTestUser(userTestName, oldPassword, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME, password);
//        userId = user.getUserID();
//        invitee = user.getUsername();
//        createInvite(projectId, userId);
//
//        litmusClient.authenticate(LoginRequest.builder().username(invitee).password(password).build());
//        List<ListInvitationResponse> response = litmusClient.listInvitation("Pending");
//        assertThat(response.size()).isEqualTo(1);
//    }
//
//    @Test
//    public void testInviteUsers() throws IOException, LitmusApiException {
//        litmusClient.authenticate(LoginRequest.builder().username(username).password(password).build());
//
//        List<String> tags = Arrays.asList("tag1", "tag2");
//        String projectName = generateUniqueProjectName("Temporary Project");
//        CreateProjectRequest projectRequest = CreateProjectRequest.builder()
//                .projectName(projectName)
//                .description("testDescription")
//                .tags(tags)
//                .build();
//
//        ProjectResponse projectResponse = litmusClient.createProject(projectRequest);
//        List<InviteUsersResponse> response1 = litmusClient.inviteUsers(projectResponse.getProjectID());
//
//        String userTestName = generateRandomUsername();
//        createPasswordUpdatedTestUser(userTestName, oldPassword, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME, password);
//
//        litmusClient.authenticate(LoginRequest.builder().username(username).password(password).build());
//        List<InviteUsersResponse> response2 = litmusClient.inviteUsers(projectResponse.getProjectID());
//
//        litmusClient.deleteProject(projectId);
//
//        assertThat(response2.size()).isEqualTo(response1.size() + 1);
//    }
//
//}
