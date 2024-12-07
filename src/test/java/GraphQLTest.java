import com.netflix.graphql.dgs.client.GraphQLResponse;
import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import java.io.IOException;

import io.litmuschaos.generated.client.ListInfrasGraphQLQuery;
import io.litmuschaos.generated.client.ListInfrasProjectionRoot;
import io.litmuschaos.generated.types.ListInfraResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphQLTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() throws IOException, LitmusApiException {
        this.litmusClient = new LitmusClient(hostUrl, username, password);
    }

    @Test
    public void listInfrasTest(){
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
