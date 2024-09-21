package io.litmuschaos.response;

public class ApiErrorResponse {
    private final String error;

    private final String errorDescription;

    public ApiErrorResponse(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
