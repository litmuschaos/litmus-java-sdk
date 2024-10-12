import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.request.ListProjectRequest;
import io.litmuschaos.response.ListProjectsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildRequestTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() throws IOException, LitmusApiException {
        this.litmusClient = new LitmusClient(hostUrl, username, password);
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
