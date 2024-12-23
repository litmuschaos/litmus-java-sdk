package io.litmuschaos.response;

import io.litmuschaos.model.LitmusAuthToken;

public class TokenCreateResponse {

    private LitmusAuthToken accessToken;
    private String type;

    public TokenCreateResponse(String accessToken, String type) {
        this.accessToken = new LitmusAuthToken(accessToken);
        this.type = type;
    }

    public LitmusAuthToken getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }
}
