package io.litmuschaos.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.exception.detailed.*;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class HttpResponseHandler {

    private final Gson gson;

    public HttpResponseHandler(){
        this.gson = new GsonBuilder().create();
    }

    public <T> T handleResponse(Response response, Type responseType) throws LitmusApiException, IOException {
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        if (response.body() == null) {
            return transform("", responseType);
        }
        return transform(response.body().string(), responseType);
    }

    private void handleErrorResponse(Response response) throws LitmusApiException, IOException {
        String body = "";
        if(response.body() != null) {
            body = response.body().string();
        }
        String errorMessage = parseErrorDescription(body);
        switch (response.code()) {
            case 400:
                throw new BadRequestException(errorMessage);
            case 401:
                throw new UnauthorizedException(errorMessage);
            case 403:
                throw new ForbiddenException(errorMessage);
            case 404:
                throw new NotFoundException(errorMessage);
            default:
                throw new InternalServerErrorException(errorMessage);
        }
    }

    private String parseErrorDescription(String errorBody) {
        JsonObject jsonObject = gson.fromJson(errorBody, JsonObject.class);
        if (jsonObject.has("errorDescription")) {
            return jsonObject.get("errorDescription").getAsString();
        }
        return "";
    }

    private <T> T transform(String response, Type responseType) {
        return gson.fromJson(response, responseType);
    }

}