package io.litmuschaos.request;

public class TokenCreateRequest {
    // Field names in token dto follow snake case convention to maintain consistency with chaos center API response format
    private final String user_id;
    private final String name;
    private final Integer days_until_expiration;

    private TokenCreateRequest(Builder builder) {
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

    public static class Builder {
        private String userID;
        private String name;
        private Integer daysUntilExpiration;

        public Builder() {}

        public Builder userID(String userId) {
            this.userID = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder daysUntilExpiration(Integer daysUntilExpiration) {
            this.daysUntilExpiration = daysUntilExpiration;
            return this;
        }

        public TokenCreateRequest build() {
            return new TokenCreateRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
