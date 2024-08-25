package io.litmuschaos.auth.http;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class LitmusHttpClient implements AutoCloseable{

    private final OkHttpClient okHttpClient;

    public LitmusHttpClient() {
        this.okHttpClient = new OkHttpClient();
    }

    public Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return okHttpClient.newCall(request).execute();
    }

    public Response post(String url, Object object) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson(object));
        Request request = new Request.Builder().url(url).post(body).build();
        return okHttpClient.newCall(request).execute();
    }

    public Response post(String url, String token, Object object) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson(object));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        return okHttpClient.newCall(request).execute();
    }

    private String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @Override
    public void close() throws Exception {
        // TODO
    }
}
