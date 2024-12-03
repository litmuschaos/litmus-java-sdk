import io.litmuschaos.LitmusClient;
import io.litmuschaos.exception.LitmusApiException;
import java.io.IOException;
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
    public void test(){
        litmusClient.graph();
    }
}
