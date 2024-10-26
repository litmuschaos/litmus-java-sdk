package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class PasswordUpdateRequest {

    private final String username;

    private final String oldPassword;

    private final String newPassword;

    private PasswordUpdateRequest(PasswordUpdateRequestBuilder builder) {
        this.username = builder.username;
        this.oldPassword = builder.oldPassword;
        this.newPassword = builder.newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public static class PasswordUpdateRequestBuilder implements Builder<PasswordUpdateRequest> {
        private String username;

        private String oldPassword;

        private String newPassword;

        public PasswordUpdateRequestBuilder(){}

        public PasswordUpdateRequestBuilder username(String username){
            this.username = username;
            return this;
        }

        public PasswordUpdateRequestBuilder oldPassword(String oldPassword){
            this.oldPassword = oldPassword;
            return this;
        }

        public PasswordUpdateRequestBuilder newPassword(String newPassword){
            this.newPassword = newPassword;
            return this;
        }

        public PasswordUpdateRequest build(){
            return new PasswordUpdateRequest(this);
        }
    }

    public static PasswordUpdateRequestBuilder builder(){
        return new PasswordUpdateRequestBuilder();
    }
}
