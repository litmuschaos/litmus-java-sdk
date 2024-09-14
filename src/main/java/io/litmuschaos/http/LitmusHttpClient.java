package io.litmuschaos.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.litmuschaos.exception.ApiException;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;

public class LitmusHttpClient implements AutoCloseable{

    private final OkHttpClient okHttpClient;

    private final Gson gson;

    private final String host;

    public LitmusHttpClient(String host) {
        this.okHttpClient = new OkHttpClient();
        this.host = host;
        this.gson = new GsonBuilder().create();
    }

    private <T> T handleResponse(Response response, Class<T> responseType) throws ApiException, IOException {
        if (!response.isSuccessful()) {
            throw new ApiException(response);
        }
        if (response.body() == null) {
            return gson.fromJson("", responseType);
        }
        return gson.fromJson(response.body().string(), responseType);
    }

    private <T> T handleResponse(Response response, Type responseType) throws ApiException, IOException {
        if (!response.isSuccessful()) {
            throw new ApiException(response);
        }
        if (response.body() == null) {
            return gson.fromJson("", responseType);
        }
        return gson.fromJson(response.body().string(), responseType);
    }

    public <T> T get(String url, Class<T> responseType) throws IOException, ApiException {
        Request request = new Request.Builder()
                .url(host + url)
                .get()
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return handleResponse(response, responseType);
        }
    }

    public <T> T get(String url, TypeToken<T> typeToken) throws IOException {
        Request request = new Request.Builder()
                .url(host + url)
                .get()
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return handleResponse(response, typeToken.getType());
        }
    }

    public <T> T post(String url, Object object, Class<T> responseType) throws IOException, ApiException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson(object));
        Request request = new Request.Builder()
                .url(host + url)
                .post(body)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return handleResponse(response, responseType);
        }
    }

    public <T> T post(String url, String token, Object object, Class<T> responseType) throws IOException, ApiException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson(object));
        Request request = new Request.Builder()
                .url(host + url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return handleResponse(response, responseType);
        }
    }

    public <T> T post(String url, String token, Class<T> responseType) throws IOException, ApiException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        Request request = new Request.Builder()
                .url(host + url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return handleResponse(response, responseType);
        }
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
