package io.litmuschaos.http;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class LitmusHttpClient implements AutoCloseable{

    private final OkHttpClient okHttpClient;

    public LitmusHttpClient() {
        this.okHttpClient = new OkHttpClient();
    }

    public <T> T get(String url, Class<T> returnType) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return transform(request, returnType);
    }

    public <T> T post(String url, Object object, Class<T> returnType) throws IOException {
        RequestBody body = RequestBody.create(toJson(object), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();
        return transform(request, returnType);
    }

    public <T> T post(String url, String token, Object object, Class<T> returnType) throws IOException {
        RequestBody body = RequestBody.create(toJson(object), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        return transform(request, returnType);
    }

    private <T> T transform(Request request, Class<T> returnType) throws IOException {
        Response response = okHttpClient.newCall(request).execute();
        if (returnType.equals(String.class)) {
            return returnType.cast(response.body().string());
        }
        return new Gson().fromJson(response.body().string(), returnType);
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
