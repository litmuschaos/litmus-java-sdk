package io.litmuschaos;

import io.litmuschaos.enums.Role;
import io.litmuschaos.exception.LitmusApiException;
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
    private static final Role TEST_USER_ROLE = Role.user;
    private static final String TEST_TOKEN = "Bearer token"; // Put your token here
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

    private UserResponse createTestUser(
            String username, String password, Role role, String email, String name
    ) throws LitmusApiException, IOException {
        UserCreateRequest request = UserCreateRequest.builder().
                username(username)
                .password(password)
                .role(role)
                .email(email)
                .name(name).build();
        return litmusClient.createUser(request);
    }
}
