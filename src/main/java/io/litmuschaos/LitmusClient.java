package io.litmuschaos;

import io.litmuschaos.exception.LitmusApiException;
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

    public LitmusClient(String host, String username, String password) throws IOException, LitmusApiException {
        this.httpClient = new LitmusHttpClient(host);
        this.authenticate(username, password);
    }
    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    // TODO - @Suyeon Jung : host, port config to LitmusAuthConfig class
    public LoginResponse authenticate(String username, String password) throws IOException, LitmusApiException {
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = httpClient.post("/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    public String createProject(String projectName) throws IOException, LitmusApiException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post("/create_project", token,  request, String.class);
    }

    public CapabilityResponse capabilities() throws IOException, LitmusApiException {
        return httpClient.get("/capabilities", CapabilityResponse.class);
    }
}
