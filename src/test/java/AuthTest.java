import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.response.CapabilityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

    private static final String HOST_URL = "http://localhost:3000";
    private static final String TEST_TOKEN = "Bear token"; // Put your token here
    private LitmusClient authClient;

    @BeforeEach
    public void setup() {
        this.authClient = new LitmusClient(HOST_URL, TEST_TOKEN);
    }

    @Test
    public void testCapabilityAPI() throws IOException, LitmusApiException {

        assertThat(authClient.capabilities())
                .isNotNull()
                .isInstanceOf(CapabilityResponse.class)
                .hasFieldOrProperty("Dex");
    }

//    @Test
//    public void testAuthenticationAPI() throws IOException, LitmusApiException {
//        LoginRequest request = LoginRequest.builder().username(username).password(password).build();
//        assertThat(authClient.authenticate(request))
//                .isNotNull()
//                .isInstanceOf(LoginResponse.class);
//    }
//
//    @Test
//    public void testAuthenticationAPIFail() {
//        // Given
//        String wrongPassword = "litmus1234";
//        LoginRequest request = LoginRequest.builder().username(username).password(wrongPassword).build();
//
//        // When & Then
//        assertThatThrownBy(() -> authClient.authenticate(request))
//                .isInstanceOf(UnauthorizedException.class);
//    }
}
