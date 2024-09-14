package io.litmuschaos;

import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.LoginRequest;
import io.litmuschaos.response.CapabilityResponse;
import io.litmuschaos.response.LoginResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LitmusClient implements AutoCloseable {

    private String token;

    private final LitmusHttpClient httpClient;

    public LitmusClient(String host, String username, String password) throws IOException {
        this.httpClient = new LitmusHttpClient(host);
        LoginResponse credential = this.authenticate(username, password);
        this.token = credential.getAccessToken();
    }

    @Override
    public void close() throws Exception {
        // TODO
    }

    // TODO - @Suyeon Jung : host, port config to LitmusAuthConfig class
    public LoginResponse authenticate(String username, String password) throws IOException {
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = httpClient.post("/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    // TODO - define Response dto
    public String createProject(String projectName) throws IOException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post("/create_project", token,  request, String.class);
    }

    public CapabilityResponse capabilities() throws IOException {
        return httpClient.get("/capabilities", CapabilityResponse.class);
    }
}