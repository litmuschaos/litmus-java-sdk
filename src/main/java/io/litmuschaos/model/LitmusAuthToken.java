package io.litmuschaos.model;

public class LitmusAuthToken {
    private String token;

    public LitmusAuthToken(String token) {
        this.token = token;
    }

    public String getTokenValue() {
        return token;
    }
}