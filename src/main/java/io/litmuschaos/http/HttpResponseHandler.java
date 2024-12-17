package io.litmuschaos.http;

import com.google.gson.*;
import io.litmuschaos.constants.ResponseBodyFields;
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
        String bodyString = "";
        if (response.body() != null) {
            bodyString = response.body().string();
            if (!bodyString.isEmpty()) {
                errorMessage = parseErrorDescription(bodyString);
            }
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
        try {
            String[] jsonObjects = errorBody.split("(?<=})(?=\\{)");

            for (String jsonStr : jsonObjects) {
                JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
                if (jsonObject.has(ResponseBodyFields.ERROR_DESCRIPTION)) {
                    return jsonObject.get(ResponseBodyFields.ERROR_DESCRIPTION).getAsString();
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    private <T> T transform(String response, Type responseType) {
        JsonElement jsonElement = JsonParser.parseString(response);

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has(ResponseBodyFields.DATA)) {
                JsonElement dataElement = jsonObject.get(ResponseBodyFields.DATA);
                return gson.fromJson(dataElement.toString(), responseType);
            }
        }
        return gson.fromJson(response, responseType);
    }

}