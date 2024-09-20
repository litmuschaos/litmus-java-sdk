package io.litmuschaos.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.litmuschaos.exception.ApiException;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class HttpResponseHandler {

    private final Gson gson;

    public HttpResponseHandler(){
        this.gson = new GsonBuilder().create();
    }

    public <T> T handleResponse(Response response, Class<T> responseType) throws ApiException, IOException {
        if (!response.isSuccessful()) {
            throw new ApiException(response);
        }
        if (response.body() == null) {
            return transform("", responseType);
        }
        return transform(response.body().string(), responseType);
    }

    public <T> T handleResponse(Response response, Type responseType) throws ApiException, IOException {
        if (!response.isSuccessful()) {
            throw new ApiException(response);
        }
        if (response.body() == null) {
            return transform("", responseType);
        }
        return transform(response.body().string(), responseType);
    }

    private <T> T transform(String response, Class<T> responseType) {
        return gson.fromJson(response, responseType);
    }

    private <T> T transform(String response, Type responseType) {
        return gson.fromJson(response, responseType);
    }
}