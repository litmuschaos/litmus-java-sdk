package io.litmuschaos.http;

import com.google.gson.*;
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
        String errorMessage = "";
        if(response.body() != null && !response.body().string().isEmpty()) {
            errorMessage = parseErrorDescription(response.body().string());
        }
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
        JsonElement jsonElement = JsonParser.parseString(response);

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("data")) {
                JsonElement dataElement = jsonObject.get("data");
                return gson.fromJson(dataElement.toString(), responseType);
            }
        }
        return gson.fromJson(response, responseType);
    }

}