import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.model.LitmusAuthToken;
import io.litmuschaos.request.UserCreateRequest;
import io.litmuschaos.request.UserDetailsUpdateRequest;
import io.litmuschaos.request.UserStateUpdateRequest;
import io.litmuschaos.response.CommonResponse;
import io.litmuschaos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String HOST_URL = "http://localhost:3000";
    private static final String CLIENT_SETUP_USERNAME = "admin";
    private static final String CLIENT_SETUP_PASSWORD = "Litmus1234!";
    private static final String TEST_USER_PASSWORD = "testPassword";
    private static final String TEST_USER_ROLE = "user";
    private static final String TEST_TOKEN_STRING = "Bearer token";
    private static final LitmusAuthToken TEST_TOKEN = new LitmusAuthToken(TEST_TOKEN_STRING);
    private static final String TEST_USER_EMAIL = "test@test.com";
    private static final String TEST_USER_NAME = "testName";

    private LitmusClient litmusClient;

    public String generateRandomUsername() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @BeforeEach
    void setup() {
        litmusClient = new LitmusClient(HOST_URL, TEST_TOKEN);
    }

    @Test
    void userCreateTest() throws IOException, LitmusApiException {
        // Given
        String username = generateRandomUsername();
        UserCreateRequest request = UserCreateRequest.builder()
                .username(username)
                .password(TEST_USER_PASSWORD)
                .role(TEST_USER_ROLE)
                .email(TEST_USER_EMAIL)
                .name(TEST_USER_NAME).build();

        // When
        UserResponse user = litmusClient.createUser(request);

        // Then
        assertThat(user).isNotNull()
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("email", TEST_USER_EMAIL)
                .hasFieldOrPropertyWithValue("name", TEST_USER_NAME)
                .hasFieldOrPropertyWithValue("role", TEST_USER_ROLE);
    }

    @Test
    void getUserTest() throws IOException, LitmusApiException {
        // Given
        String username = generateRandomUsername();
        UserResponse createdUser = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);

        // When
        UserResponse user = litmusClient.getUser(createdUser.getUserID());
        List<UserResponse> users = litmusClient.getUsers();

        // Then
        assertThat(user).isNotNull()
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("email", TEST_USER_EMAIL)
                .hasFieldOrPropertyWithValue("name", TEST_USER_NAME)
                .hasFieldOrPropertyWithValue("role", TEST_USER_ROLE);

        assertThat(users).isNotNull()
                .hasSizeGreaterThanOrEqualTo(1)
                .anyMatch(response -> response.getUserID().equals(createdUser.getUserID()));
    }

    @Test
    void updateUserStateDeactivateTest() throws IOException, LitmusApiException {
        // Given
        String username = generateRandomUsername();
        UserResponse createdUser = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);

        // When
        UserStateUpdateRequest stateUpdateRequest = UserStateUpdateRequest.builder()
                .username(username)
                .isDeactivate(true)
                .build();
        litmusClient.updateUserState(stateUpdateRequest);

        // Then
        UserResponse user = litmusClient.getUser(createdUser.getUserID());
        assertThat(user.isRemoved()).isTrue();
    }

//    @Test
//    void updateUserStateActivateTest() throws IOException, LitmusApiException {
//        // Given
//        String username = generateRandomUsername();
//        UserResponse createdUser = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);
//        UserStateUpdateRequest stateUpdateRequest = UserStateUpdateRequest.builder()
//                .username(username)
//                .isDeactivate(true)
//                .build();
//        litmusClient.updateUserState(stateUpdateRequest);
//
//        // When
//        stateUpdateRequest = UserStateUpdateRequest.builder()
//                .username(username)
//                .isDeactivate(false)
//                .build();
//        litmusClient.updateUserState(stateUpdateRequest);
//
//        // Then
//        UserResponse user = litmusClient.getUser(createdUser.getUserID());
//        assertThat(user.isRemoved()).isFalse();
//    }
//
//    @Test
//    void updatePasswordTest() throws IOException, LitmusApiException {
//        // Given
//        String username = generateRandomUsername();
//        UserResponse createdUser = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);
//        String newPassword = "newPassword1!";
//
//        // When
//        PasswordUpdateRequest request = PasswordUpdateRequest.builder()
//                .username(createdUser.getUsername())
//                .oldPassword(TEST_USER_PASSWORD)
//                .newPassword(newPassword)
//                .build();
//
//        // Then
//        // Only password owner can update own password
//        // admin user try to update user's password -> fail
//        assertThatThrownBy(() -> litmusClient.updatePassword(request))
//                .isInstanceOf(UnauthorizedException.class);
//
//        // authenticate by password owner's username, password then change password
//        litmusClient.authenticate(LoginRequest.builder().username(createdUser.getUsername()).password(TEST_USER_PASSWORD).build());
//        assertThat(litmusClient.updatePassword(request))
//                .isNotNull()
//                .hasFieldOrProperty("message")
//                .hasFieldOrProperty("projectID");
//    }

//    @Test
//    void resetPasswordTest() throws IOException, LitmusApiException {
//        // Given
//        String username = generateRandomUsername();
//        createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);
//        String newPassword = "newPassword1!";
//        PasswordResetRequest request = PasswordResetRequest.builder()
//                .username(username)
//                .newPassword(newPassword)
//                .build();
//
//        // User cannot call password reset API
//        litmusClient.authenticate(LoginRequest.builder().username(username).password(TEST_USER_PASSWORD).build());
//        assertThatThrownBy(() -> litmusClient.resetPassword(request))
//                .isInstanceOf(UnauthorizedException.class);
//
//        // Admin can call password reset API
//        litmusClient.authenticate(LoginRequest.builder().username(CLIENT_SETUP_USERNAME).password(CLIENT_SETUP_PASSWORD).build());
//        assertThat(litmusClient.resetPassword(request))
//                .isNotNull()
//                .isInstanceOf(CommonResponse.class);
//    }

    @Test
    void updateUserDetailsTest() throws IOException, LitmusApiException {
        // Given
        String username = generateRandomUsername();
        UserResponse user = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);
        String newName = "newName";
        String newEmail = "new@new.com";

        // When & Then
        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .userID(user.getUserID())
                .name(newName)
                .email(newEmail).build();

        assertThat(litmusClient.updateUserDetails(request))
                .isNotNull()
                .isInstanceOf(CommonResponse.class);
    }

//    @Test
//    void createTokenTest() throws IOException, LitmusApiException {
//        // Given
//        String username = generateRandomUsername();
//        UserResponse user = createPasswordUpdatedTestUser(
//                username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME, "newPassword1!"
//        );
//
//        // When
//        TokenCreateRequest request = TokenCreateRequest.builder()
//                .userID(user.getUserID())
//                .name("test token")
//                .daysUntilExpiration(30).build();
//        TokenCreateResponse tokenCreateResponse = litmusClient.createToken(request);
//
//        // Then
//        List<TokenResponse> tokens = litmusClient.getTokens(user.getUserID()).getTokens();
//        assertThat(tokens).anyMatch(
//                token ->
//                        token.getUserID().equals(user.getUserID())
//                                && token.getToken().equals(tokenCreateResponse.getAccessToken())
//        );
//    }

//    @Test
//    void deleteTokenTest() throws IOException, LitmusApiException {
//        // Given
//        String username = generateRandomUsername();
//        UserResponse user = createPasswordUpdatedTestUser(
//                username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME, "newPassword1!"
//        );
//        TokenCreateRequest tokenCreateRequest = TokenCreateRequest.builder()
//                .userID(user.getUserID())
//                .name("test token")
//                .daysUntilExpiration(30).build();
//        TokenCreateResponse tokenCreateResponse = litmusClient.createToken(tokenCreateRequest);
//
//        // When
//        TokenDeleteRequest tokenDeleteRequest = TokenDeleteRequest.builder()
//                .userID(user.getUserID())
//                .token(tokenCreateResponse.getAccessToken()).build();
//        litmusClient.deleteToken(tokenDeleteRequest);
//
//        // Then
//        ListTokensResponse response = litmusClient.getTokens(user.getUserID());
//        assertThat(response.getTokens()).isNull();
//    }

    private UserResponse createTestUser(
            String username, String password, String role, String email, String name
    ) throws LitmusApiException, IOException {
        UserCreateRequest request = UserCreateRequest.builder().
                username(username)
                .password(password)
                .role(role)
                .email(email)
                .name(name).build();
        return litmusClient.createUser(request);
    }

//    private UserResponse createPasswordUpdatedTestUser(
//            String username, String password, String role, String email, String name, String newPassword
//    ) throws LitmusApiException, IOException {
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
}
