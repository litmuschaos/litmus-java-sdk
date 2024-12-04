import com.netflix.graphql.dgs.client.GraphQLResponse;
import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphQLTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String graphQLUrl = "http://localhost:8080/query";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() throws IOException, LitmusApiException {
        this.litmusClient = new LitmusClient(hostUrl, username, password);
    }

    @Test
    public void listInfrasTest(){
        GraphQLResponse result = litmusClient.listInfras(
            "c01699ba-2d16-4052-bfc6-400332fd2b9a",
            List.of("demo"));
        System.out.println(result.getData());
    }
}
