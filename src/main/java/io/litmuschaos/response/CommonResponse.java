package io.litmuschaos.response;

public class CommonResponse {

    private String message;

    public CommonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
