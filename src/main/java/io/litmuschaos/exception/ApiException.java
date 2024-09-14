package io.litmuschaos.exception;

import com.google.gson.Gson;
import io.litmuschaos.response.ApiErrorResponse;
import okhttp3.Response;

public class ApiException {
    private final int statusCode;
    private final ApiErrorResponse errorResponse;

    public ApiException(Response response) {
        this.statusCode = response.code();
        this.errorResponse = parseErrorResponse(response);
    }

    private static ApiErrorResponse parseErrorResponse(Response response) {
        if(response.body() == null){
            return new Gson().fromJson("", ApiErrorResponse.class);
        }
        return new Gson().fromJson(response.body().toString(), ApiErrorResponse.class);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ApiErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
