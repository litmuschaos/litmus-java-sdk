package io.litmuschaos.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.litmuschaos.exception.ApiException;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class HttpResponseHandler {

    private final Gson gson;

    public HttpResponseHandler(){
        this.gson = new GsonBuilder().create();
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

    private <T> T transform(String responseBody, Type responseType) {
        JsonElement jsonElement = JsonParser.parseString(responseBody);

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("data")) {
                JsonElement dataElement = jsonObject.get("data");
                return gson.fromJson(dataElement.toString(), responseType);
            }
        }

        return gson.fromJson(responseBody, responseType);
    }

}