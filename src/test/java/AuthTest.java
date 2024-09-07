import io.litmuschaos.LitmusClient;
import io.litmuschaos.response.LoginResponse;
import okhttp3.Response;

import java.io.IOException;


public class AuthTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    public static void main(String[] args) throws IOException {

        LitmusClient authClient = new LitmusClient(hostUrl, username, password);

        System.out.println("### capabilities test");
        Response response = authClient.capabilities(hostUrl);
        System.out.println(response.body().string());

        System.out.println("### craeteProject test");
        response = authClient.createProject(
                hostUrl,
                "TEST_Project_1");
        System.out.println(response.body().string());

        LoginResponse loginResponse = authClient.authenticate(hostUrl, username, password);
        System.out.println("### refresh token test");
        System.out.println("access Token :: " + loginResponse.getAccessToken());
    }
}
