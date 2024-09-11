package io.litmuschaos;

import com.google.gson.GsonBuilder;
import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.LoginRequest;
import io.litmuschaos.response.LoginResponse;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LitmusClient implements AutoCloseable {

    private String token;

    private final LitmusHttpClient httpClient = new LitmusHttpClient();

    public LitmusClient(String host, String username, String password) throws IOException {
        LoginResponse credential = this.authenticate(host, username, password);
        this.token = credential.getAccessToken();
    }

    @Override
    public void close() throws Exception {
        // TODO
    }

    // TODO - @Suyeon Jung : host, port config to LitmusAuthConfig class
    public LoginResponse authenticate(String host, String username, String password) throws IOException {
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = httpClient.post(host + "/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    // TODO - define Response dto
    public String createProject(String host, String projectName) throws IOException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post(host + "/create_project", token,  request, String.class);
    }

    // TODO - define Response dto
    public String capabilities(String host) throws IOException {
        return httpClient.get(host + "/capabilities", String.class);
    }

}