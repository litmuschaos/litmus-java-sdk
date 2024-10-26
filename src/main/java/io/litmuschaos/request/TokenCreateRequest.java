package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class TokenCreateRequest {
    // Field names in token dto follow snake case convention to maintain consistency with chaos center API response format
    private final String user_id;
    private final String name;
    private final Integer days_until_expiration;

    private TokenCreateRequest(TokenCreateRequestBuilder builder) {
        this.user_id = builder.userID;
        this.name = builder.name;
        this.days_until_expiration = builder.daysUntilExpiration;
    }

    public String getUserID() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public Integer getDaysUntilExpiration() {
        return days_until_expiration;
    }

    public static class TokenCreateRequestBuilder implements Builder<TokenCreateRequest> {
        private String userID;
        private String name;
        private Integer daysUntilExpiration;

        public TokenCreateRequestBuilder() {}

        public TokenCreateRequestBuilder userID(String userId) {
            this.userID = userId;
            return this;
        }

        public TokenCreateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TokenCreateRequestBuilder daysUntilExpiration(Integer daysUntilExpiration) {
            this.daysUntilExpiration = daysUntilExpiration;
            return this;
        }

        public TokenCreateRequest build() {
            return new TokenCreateRequest(this);
        }
    }

    public static TokenCreateRequestBuilder builder() {
        return new TokenCreateRequestBuilder();
    }
}
