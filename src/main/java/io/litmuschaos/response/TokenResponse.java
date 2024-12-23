package io.litmuschaos.response;

import io.litmuschaos.model.LitmusAuthToken;

public class TokenResponse {
    // Field names in token dto follow snake case convention to maintain consistency with chaos center API response format
    private String user_id;
    private String name;
    private LitmusAuthToken token;
    private Long expires_at;
    private Long created_at;

    public TokenResponse(String userId, String name, String token, Long expiresAt, Long createdAt) {
        this.user_id = userId;
        this.name = name;
        this.token = new LitmusAuthToken(token);
        this.expires_at = expiresAt;
        this.created_at = createdAt;
    }

    public String getUserID() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public LitmusAuthToken getToken() {
        return token;
    }

    public Long getExpiresAt() {
        return expires_at;
    }

    public Long getCreatedAt() {
        return created_at;
    }
}
