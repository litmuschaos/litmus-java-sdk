package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class TokenDeleteRequest {
    private final String userID;
    private final String token;

    private TokenDeleteRequest(TokenDeleteRequestBuilder builder) {
        this.userID = builder.userID;
        this.token = builder.token;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return token;
    }


    public static class TokenDeleteRequestBuilder implements Builder<TokenDeleteRequest> {
        private String userID;
        private String token;

        public TokenDeleteRequestBuilder() {}

        public TokenDeleteRequestBuilder userID(String userId) {
            this.userID = userId;
            return this;
        }

        public TokenDeleteRequestBuilder token(String token) {
            this.token = token;
            return this;
        }

        public TokenDeleteRequest build() {
            return new TokenDeleteRequest(this);
        }
    }

    public static TokenDeleteRequestBuilder builder() {
        return new TokenDeleteRequestBuilder();
    }
}
