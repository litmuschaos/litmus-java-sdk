package io.litmuschaos;

import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.ListProjectRequest;
import io.litmuschaos.response.ListProjectsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
public class BuildRequestTest {

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() throws IOException, LitmusApiException {
        IntegrationTestConfig.assumeConfigured();
        this.litmusClient = new LitmusClient(IntegrationTestConfig.hostUrl(), IntegrationTestConfig.token());
    }

    @Test
    public void testGetListProjects() throws IOException, LitmusApiException {
        int page = 0;
        int limit = 10;
        String sortField = "name";
        boolean createdByMe = true;

        ListProjectRequest request = ListProjectRequest.builder()
                .page(page)
                .limit(limit)
                .sortField(sortField)
                .createdByMe(createdByMe)
                .build();

        ListProjectsResponse response = litmusClient.listProjects(request);

        assertThat(response.getTotalNumberOfProjects())
                .isGreaterThanOrEqualTo(1);
    }
}
