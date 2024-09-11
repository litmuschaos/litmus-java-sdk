import io.litmuschaos.LitmusClient;

import java.io.IOException;


public class AuthTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    public static void main(String[] args) throws IOException {

        LitmusClient authClient = new LitmusClient(hostUrl, username, password);

        System.out.println("### capabilities test");
        var capabilities = authClient.capabilities(hostUrl);
        System.out.println(capabilities);

        System.out.println("### createProject test");
        var project = authClient.createProject(
                hostUrl,
                "TEST_Project_5");
        System.out.println(project);

        var auth = authClient.authenticate(hostUrl, username, password);
        System.out.println("### refresh token test");
        System.out.println("access Token :: " + auth.getAccessToken());
    }
}
