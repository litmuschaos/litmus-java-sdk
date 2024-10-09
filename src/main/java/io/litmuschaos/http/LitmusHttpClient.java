package io.litmuschaos.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.litmuschaos.exception.LitmusApiException;
import okhttp3.*;

import java.io.IOException;

public class LitmusHttpClient implements AutoCloseable{

    private final OkHttpClient okHttpClient;
    private final HttpResponseHandler httpResponseHandler;
    private final String host;

    public LitmusHttpClient(String host) {
        this.okHttpClient = new OkHttpClient();
        this.httpResponseHandler = new HttpResponseHandler();
        this.host = host;
    }

    public <T> T get(String url, Class<T> responseType) throws IOException, LitmusApiException {
        Request request = new Request.Builder()
                .url(host + url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, responseType);
    }

    public <T> T get(String url, TypeToken<T> typeToken) throws IOException, LitmusApiException {
        Request request = new Request.Builder()
                .url(host + url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, typeToken.getType());
    }

    public <T> T post(String url, Object object, Class<T> responseType) throws IOException, LitmusApiException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson(object));
        Request request = new Request.Builder()
                .url(host + url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, responseType);
    }

    public <T> T post(String url, String token, Object object, Class<T> responseType) throws IOException, LitmusApiException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson(object));
        Request request = new Request.Builder()
                .url(host + url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, responseType);
    }

    public <T> T post(String url, String token, Class<T> responseType) throws IOException, LitmusApiException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        Request request = new Request.Builder()
                .url(host + url)
                .post(body)
                .header("Authorization", "Bearer " + token)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, responseType);
    }

    private String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @Override
    public void close() throws Exception {
        this.okHttpClient.dispatcher().executorService().shutdown();
        this.okHttpClient.connectionPool().evictAll();
        if (this.okHttpClient.cache() != null) {
            this.okHttpClient.cache().close();
        }
    }
}
