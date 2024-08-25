package io.litmuschaos;

import com.google.gson.GsonBuilder;
import io.litmuschaos.auth.http.LitmusHttpClient;
import io.litmuschaos.auth.request.LoginRequest;
import io.litmuschaos.auth.response.LoginResponse;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// TODO - logging
public class LitmusAuthClient implements AutoCloseable{

    private String token;

    private final LitmusHttpClient httpClient = new LitmusHttpClient();

    public LitmusAuthClient(String host, String username, String password) throws IOException {
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
        Response response = httpClient.post(host + "/login", request);
        LoginResponse loginResponse = new GsonBuilder().create().fromJson(response.body().string(), LoginResponse.class);
        this.token = loginResponse.getAccessToken();
        return loginResponse;
    }

    // TODO - define Response dto
    public Response createProject(String host, String projectName) throws IOException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post(host + "/create_project", token,  request);
    }

    // TODO - define Response dto
    public Response capabilities(String host) throws IOException {
        return httpClient.get(host + "/capabilities");
    }

}