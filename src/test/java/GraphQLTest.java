import io.litmuschaos.LitmusClient;
import io.litmuschaos.generated.client.CreateEnvironmentGraphQLQuery;
import io.litmuschaos.generated.client.CreateEnvironmentProjectionRoot;
import io.litmuschaos.generated.client.DeleteEnvironmentGraphQLQuery;
import io.litmuschaos.generated.client.ListInfrasGraphQLQuery;
import io.litmuschaos.generated.client.ListInfrasProjectionRoot;
import io.litmuschaos.generated.types.CreateEnvironmentRequest;
import io.litmuschaos.generated.types.Environment;
import io.litmuschaos.generated.types.EnvironmentType;
import io.litmuschaos.generated.types.ListInfraResponse;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphQLTest {

    private static final String HOST_URL = "http://127.0.0.1:55483";
    private static final String TEST_TOKEN = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ4ODc2MTQ3MDQsInJvbGUiOiJhZG1pbiIsInVpZCI6ImU5YTg5MjY4LWFhMWItNDBlZC05NjZlLTNjNmIyMjMwNWU5YSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.J3qpbQDTtF_ghrQB3h-uTFPqr7UGxR5-59CfzJQ6tqSkeOi-lhaBgwMlR80-F0-U9ZovDMcfD55zeQLA63v58g";

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() {
        this.litmusClient = new LitmusClient(HOST_URL, TEST_TOKEN);
    }

    @Test
    public void listInfrasTest() {
        ListInfraResponse result = litmusClient.listInfras(
                new ListInfrasGraphQLQuery
                        .Builder()
                        .projectID("3f397b8c-292f-4d4d-b5dd-447e3205acd1")
                        .build(),
                new ListInfrasProjectionRoot<>()
                        .infras()
                        .infraID()
                        .name()
                        .parent()
                        .totalNoOfInfras()
        );
        System.out.println(result.getInfras());
    }

    @Test
    public void createEnvironmentTest() {

        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
        int random = new Random().nextInt();
        String environmentID = "TEST-ENVIRONMENT-ID-" + random;
        String environmentName = "TEST-ENVIRONMENT-NAME-" + random;


        Environment result = litmusClient.createEnvironment(
            CreateEnvironmentGraphQLQuery.newRequest()
                .projectID(projectId)
                .request(CreateEnvironmentRequest
                    .newBuilder()
                    .environmentID(environmentID)
                    .name(environmentName)
                    .type(EnvironmentType.NON_PROD)
                    .build()
                )
                .build(),
            new CreateEnvironmentProjectionRoot<>()
                .environmentID()
        );

        Assertions.assertThat(result.getEnvironmentID()).isEqualTo(environmentID);
    }

    @Test
    public void deleteEnvironmentTest() {

        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
        int random = new Random().nextInt();
        String environmentID = "TEST-ENVIRONMENT-ID-" + random;
        String environmentName = "TEST-ENVIRONMENT-NAME-" + random;

        litmusClient.createEnvironment(
            CreateEnvironmentGraphQLQuery.newRequest()
                .projectID(projectId)
                .request(CreateEnvironmentRequest
                    .newBuilder()
                    .environmentID(environmentID)
                    .name(environmentName)
                    .type(EnvironmentType.NON_PROD)
                    .build()
                )
                .build(),
            new CreateEnvironmentProjectionRoot<>()
                .environmentID()
        );

        String result = litmusClient.deleteEnvironment(
            DeleteEnvironmentGraphQLQuery.newRequest()
                .projectID(projectId)
                .environmentID(environmentID)
                .build()
        );

        Assertions.assertThat(result).isEqualTo("successfully deleted environment");
    }
}
