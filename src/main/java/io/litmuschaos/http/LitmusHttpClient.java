package io.litmuschaos.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.litmuschaos.exception.LitmusApiException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

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
        return get(url, null, null, responseType);
    }

    public <T> T get(String url, String token, Class<T> responseType) throws IOException, LitmusApiException {
        return get(url, token, null, responseType);
    }

    public <T> T get(String url, String token, Map<String, String> requestParamMap, Class<T> responseType) throws IOException, LitmusApiException {
        Request request = buildRequest(url, token, requestParamMap);
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, responseType);
    }

    public <T> T get(String url, TypeToken<T> typeToken) throws IOException, LitmusApiException {
        return get(url, null, null, typeToken);
    }

    public <T> T get(String url, String token, Map<String, String> requestParamMap, TypeToken<T> typeToken) throws IOException, LitmusApiException {
        Request request = buildRequest(url, token, requestParamMap);
        Response response = okHttpClient.newCall(request).execute();
        return httpResponseHandler.handleResponse(response, typeToken.getType());
    }

    private Request buildRequest(String url, String token, Map<String, String> requestParamMap) throws IOException {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.get(host + url).newBuilder();

        if (requestParamMap != null) {
            for(Map.Entry<String, String> param : requestParamMap.entrySet()) {
                httpUrlBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(httpUrlBuilder.build())
                .get();

        if (StringUtils.isNotEmpty(token)) {
            requestBuilder
                    .header("Authorization", "Bearer " + token);
        }

        return requestBuilder.build();
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
