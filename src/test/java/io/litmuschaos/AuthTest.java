package io.litmuschaos;

import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.response.CapabilityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
public class AuthTest {

    private LitmusClient authClient;

    @BeforeEach
    public void setup() {
        IntegrationTestConfig.assumeConfigured();
        this.authClient = new LitmusClient(IntegrationTestConfig.hostUrl(), IntegrationTestConfig.token());
    }

    @Test
    public void testCapabilityAPI() throws IOException, LitmusApiException {

        assertThat(authClient.capabilities())
                .isNotNull()
                .isInstanceOf(CapabilityResponse.class)
                .hasFieldOrProperty("Dex");
    }
}
