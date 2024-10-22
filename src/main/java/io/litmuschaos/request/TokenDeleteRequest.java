package io.litmuschaos.request;

public class TokenDeleteRequest {
    private final String userID;
    private final String token;

    private TokenDeleteRequest(TokenDeleteRequest.Builder builder) {
        this.userID = builder.userID;
        this.token = builder.token;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return token;
    }


    public static class Builder {
        private String userID;
        private String token;

        public Builder() {}

        public Builder userID(String userId) {
            this.userID = userId;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public TokenDeleteRequest build() {
            return new TokenDeleteRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
