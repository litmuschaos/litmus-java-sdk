import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.exception.detailed.UnauthorizedException;
import io.litmuschaos.request.*;
import io.litmuschaos.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String HOST_URL = "http://localhost:3000";
    private static final String CLIENT_SETUP_USERNAME = "admin";
    private static final String CLIENT_SETUP_PASSWORD = "Litmus1234!";
    private static final String TEST_USER_PASSWORD = "testPassword";
    private static final String TEST_USER_ROLE = "user";
    private static final String TEST_USER_EMAIL = "test@test.com";
    private static final String TEST_USER_NAME = "testName";

    private String randomUsername;
    private LitmusClient litmusClient;

    public String generateRandomUsername() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @BeforeEach
    void setup() throws IOException, LitmusApiException {
        litmusClient = new LitmusClient(HOST_URL, CLIENT_SETUP_USERNAME, CLIENT_SETUP_PASSWORD);
        randomUsername = generateRandomUsername();
    }

    @Test
    void userCreateTest() throws IOException, LitmusApiException {
        UserCreateRequest request = makeTestUserCreateRequest();
        assertThat(litmusClient.createUser(request))
                .isNotNull()
                .hasFieldOrPropertyWithValue("username", randomUsername)
                .hasFieldOrPropertyWithValue("email", TEST_USER_EMAIL)
                .hasFieldOrPropertyWithValue("name", TEST_USER_NAME)
                .hasFieldOrPropertyWithValue("role", TEST_USER_ROLE);
    }

    @Test
    void getUserTest() throws IOException, LitmusApiException {
        UserCreateRequest request = makeTestUserCreateRequest();
        UserResponse userResponse = litmusClient.createUser(request);
        assertThat(litmusClient.getUser(userResponse.getUserID()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("username", randomUsername)
                .hasFieldOrPropertyWithValue("email", TEST_USER_EMAIL)
                .hasFieldOrPropertyWithValue("name", TEST_USER_NAME)
                .hasFieldOrPropertyWithValue("role", TEST_USER_ROLE);

        assertThat(litmusClient.getUsers())
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(1)
                .anyMatch(response -> response.getUserID().equals(userResponse.getUserID()));
    }

    @Test
    void updateUserStateTest() throws IOException, LitmusApiException {
        UserResponse createdUser = litmusClient.createUser(makeTestUserCreateRequest());
        String userID = createdUser.getUserID();

        UserStateUpdateRequest stateUpdateRequest = UserStateUpdateRequest.builder()
                .username(randomUsername)
                .isDeactivate(true)
                .build();

        assertThat(litmusClient.updateUserState(stateUpdateRequest)).isNotNull();

        UserResponse user = litmusClient.getUser(userID);
        assertThat(user.isRemoved()).isTrue();

        stateUpdateRequest = UserStateUpdateRequest.builder()
                .username(randomUsername)
                .isDeactivate(false)
                .build();
        litmusClient.updateUserState(stateUpdateRequest);
        user = litmusClient.getUser(userID);
        assertThat(user.isRemoved()).isFalse();
    }

    @Test
    void updatePasswordTest() throws IOException, LitmusApiException {
        litmusClient.createUser(makeTestUserCreateRequest());
        String newPassword = "newPassword1!";
        PasswordUpdateRequest request = PasswordUpdateRequest.builder()
                .username(randomUsername)
                .oldPassword(TEST_USER_PASSWORD)
                .newPassword(newPassword)
                .build();

        // Only password owner can update own password
        // admin user try to update user's password -> fail
        assertThatThrownBy(() -> litmusClient.updatePassword(request))
                .isInstanceOf(UnauthorizedException.class);


        // authenticate by password owner's username, password then change password
        litmusClient.authenticate(LoginRequest.builder().username(randomUsername).password(TEST_USER_PASSWORD).build());
        assertThat(litmusClient.updatePassword(request))
                .isNotNull()
                .hasFieldOrProperty("message")
                .hasFieldOrProperty("projectID");
    }

    @Test
    void resetPasswordTest() throws IOException, LitmusApiException {
        litmusClient.createUser(makeTestUserCreateRequest());
        String newPassword = "newPassword1!";
        PasswordResetRequest request = PasswordResetRequest.builder()
                .username(randomUsername)
                .newPassword(newPassword)
                .build();

        // User cannot call password reset API
        litmusClient.authenticate(LoginRequest.builder().username(randomUsername).password(TEST_USER_PASSWORD).build());
        assertThatThrownBy(() -> litmusClient.resetPassword(request))
                .isInstanceOf(UnauthorizedException.class);

        // Admin can call password reset API
        litmusClient.authenticate(LoginRequest.builder().username(CLIENT_SETUP_USERNAME).password(CLIENT_SETUP_PASSWORD).build());
        assertThat(litmusClient.resetPassword(request))
                .isNotNull()
                .isInstanceOf(CommonResponse.class);
    }

    @Test
    void updateUserDetailsTest() throws IOException, LitmusApiException {
        UserResponse user = litmusClient.createUser(makeTestUserCreateRequest());
        String newName = "newName";
        String newEmail = "new@new.com";
        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .userID(user.getUserID())
                .name(newName)
                .email(newEmail).build();

        assertThat(litmusClient.updateUserDetails(request))
                .isNotNull()
                .isInstanceOf(CommonResponse.class);
    }

    @Test
    void createTokenTest() throws IOException, LitmusApiException {
        UserResponse user = litmusClient.createUser(makeTestUserCreateRequest());
        litmusClient.authenticate(LoginRequest.builder().username(randomUsername).password(TEST_USER_PASSWORD).build());
        PasswordUpdateRequest passwordUpdateRequest = PasswordUpdateRequest.builder()
                .username(randomUsername)
                .oldPassword(TEST_USER_PASSWORD)
                .newPassword("newPassword1!")
                .build();
        litmusClient.updatePassword(passwordUpdateRequest);

        TokenCreateRequest request = TokenCreateRequest.builder()
                .userID(user.getUserID())
                .name("test token")
                .daysUntilExpiration(30)
                .build();
        TokenCreateResponse tokenCreateResponse = litmusClient.createToken(request);
        List<TokenResponse> tokens = litmusClient.getTokens(user.getUserID()).getTokens();
        assertThat(tokens).anyMatch(
                token ->
                        token.getUserID().equals(user.getUserID())
                                && token.getToken().equals(tokenCreateResponse.getAccessToken())
        );
    }

    @Test
    void deleteTokenTest() throws IOException, LitmusApiException {
        UserResponse user = litmusClient.createUser(makeTestUserCreateRequest());
        litmusClient.authenticate(LoginRequest.builder().username(randomUsername).password(TEST_USER_PASSWORD).build());
        PasswordUpdateRequest passwordUpdateRequest = PasswordUpdateRequest.builder()
                .username(randomUsername)
                .oldPassword(TEST_USER_PASSWORD)
                .newPassword("newPassword1!")
                .build();
        litmusClient.updatePassword(passwordUpdateRequest);

        // create token
        TokenCreateRequest tokenCreateRequest = TokenCreateRequest.builder()
                .userID(user.getUserID())
                .name("test token")
                .daysUntilExpiration(30)
                .build();
        TokenCreateResponse tokenCreateResponse = litmusClient.createToken(tokenCreateRequest);

        // delete token
        TokenDeleteRequest tokenDeleteRequest = TokenDeleteRequest.builder()
                .userID(user.getUserID())
                .token(tokenCreateResponse.getAccessToken())
                .build();

        litmusClient.deleteToken(tokenDeleteRequest);
        ListTokensResponse response = litmusClient.getTokens(user.getUserID());
        assertThat(response.getTokens()).isNull();
    }

    private UserCreateRequest makeTestUserCreateRequest(){
        return UserCreateRequest.builder()
                .username(randomUsername)
                .password(TEST_USER_PASSWORD)
                .role(TEST_USER_ROLE)
                .email(TEST_USER_EMAIL)
                .name(TEST_USER_NAME).build();
    }

}
