package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class UserStateUpdateRequest {

    private final String username;
    private final boolean isDeactivate;

    private UserStateUpdateRequest(UserStateUpdateRequestBuilder builder) {
        this.username = builder.username;
        this.isDeactivate = builder.isDeactivate;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDeactivate() {
        return isDeactivate;
    }

    public static UserStateUpdateRequestBuilder builder(){
        return new UserStateUpdateRequestBuilder();
    }

    public static class UserStateUpdateRequestBuilder implements Builder<UserStateUpdateRequest> {
        private String username;
        private boolean isDeactivate;

        public UserStateUpdateRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserStateUpdateRequestBuilder isDeactivate(boolean isDeactivate) {
            this.isDeactivate = isDeactivate;
            return this;
        }

        public UserStateUpdateRequest build(){
            return new UserStateUpdateRequest(this);
        }
    }
}
