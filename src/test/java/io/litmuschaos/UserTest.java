package io.litmuschaos;

import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.UserCreateRequest;
import io.litmuschaos.request.UserDetailsUpdateRequest;
import io.litmuschaos.request.UserStateUpdateRequest;
import io.litmuschaos.response.CommonResponse;
import io.litmuschaos.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String HOST_URL = "http://localhost:3000";
    private static final String TEST_TOKEN = "Bearer token"; // Put your token here
    private static final String TEST_USER_PASSWORD = "testPassword";
    private static final String TEST_USER_ROLE = "user";
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
        String username = generateRandomUsername();
        UserCreateRequest request = UserCreateRequest.builder()
                .username(username)
                .password(TEST_USER_PASSWORD)
                .role(TEST_USER_ROLE)
                .email(TEST_USER_EMAIL)
                .name(TEST_USER_NAME).build();

        UserResponse user = litmusClient.createUser(request);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(TEST_USER_EMAIL, user.getEmail());
        assertEquals(TEST_USER_NAME, user.getName());
        assertEquals(TEST_USER_ROLE, user.getRole());
    }

    @Test
    void getUserTest() throws IOException, LitmusApiException {
        String username = generateRandomUsername();
        UserResponse createdUser = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);

        UserResponse user = litmusClient.getUser(createdUser.getUserID());
        List<UserResponse> users = litmusClient.getUsers();

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(TEST_USER_EMAIL, user.getEmail());
        assertEquals(TEST_USER_NAME, user.getName());
        assertEquals(TEST_USER_ROLE, user.getRole());

        assertNotNull(users);
        assertTrue(users.size() >= 1);
        assertTrue(users.stream().anyMatch(response -> response.getUserID().equals(createdUser.getUserID())));
    }

    @Test
    void updateUserStateDeactivateTest() throws IOException, LitmusApiException {
        String username = generateRandomUsername();
        UserResponse createdUser = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);

        UserStateUpdateRequest stateUpdateRequest = UserStateUpdateRequest.builder()
                .username(username)
                .isDeactivate(true)
                .build();
        litmusClient.updateUserState(stateUpdateRequest);

        UserResponse user = litmusClient.getUser(createdUser.getUserID());
        assertTrue(user.isRemoved());
    }

    @Test
    void updateUserDetailsTest() throws IOException, LitmusApiException {
        String username = generateRandomUsername();
        UserResponse user = createTestUser(username, TEST_USER_PASSWORD, TEST_USER_ROLE, TEST_USER_EMAIL, TEST_USER_NAME);
        String newName = "newName";
        String newEmail = "new@new.com";

        UserDetailsUpdateRequest request = UserDetailsUpdateRequest.builder()
                .userID(user.getUserID())
                .name(newName)
                .email(newEmail).build();

        CommonResponse response = litmusClient.updateUserDetails(request);
        assertNotNull(response);
    }


    
    private UserResponse createTestUser(
            String username, String password, String role, String email, String name
    ) throws LitmusApiException, IOException {
        UserCreateRequest request = UserCreateRequest.builder()
                .username(username)
                .password(password)
                .role(role)
                .email(email)
                .name(name).build();
        return litmusClient.createUser(request);
    }
}
