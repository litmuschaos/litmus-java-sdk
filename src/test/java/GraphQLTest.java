import io.litmuschaos.LitmusClient;
import io.litmuschaos.generated.client.ListInfrasGraphQLQuery;
import io.litmuschaos.generated.client.ListInfrasProjectionRoot;
import io.litmuschaos.generated.types.ListInfraResponse;
import io.litmuschaos.model.LitmusAuthToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphQLTest {

    private static final String HOST_URL = "http://localhost:3000";
    private static final String TEST_TOKEN_STRING = "Bearer token";
    private static final LitmusAuthToken TEST_TOKEN = new LitmusAuthToken(TEST_TOKEN_STRING);

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
                        .projectID("8d2dc452-00dc-4ff9-968f-b8105385ecdb")
                        .build(),
                new ListInfrasProjectionRoot<>()
                        .infras()
                        .infraID()
                        .name()
                        .parent()
                        .totalNoOfInfras()
        );
        System.out.println(result.getTotalNoOfInfras());
    }
}
