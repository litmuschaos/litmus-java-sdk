package io.litmuschaos.response;

public class TokenCreateResponse {

    private String accessToken;
    private String type;

    public TokenCreateResponse(String accessToken, String type) {
        this.accessToken = accessToken;
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }
}
