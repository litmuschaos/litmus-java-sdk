package io.litmuschaos.request;

public class PasswordUpdateRequest {

    private final String username;

    private final String oldPassword;

    private final String newPassword;

    private PasswordUpdateRequest(Builder builder) {
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

    public static class Builder {
        private String username;

        private String oldPassword;

        private String newPassword;

        public Builder(){}

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder oldPassword(String oldPassword){
            this.oldPassword = oldPassword;
            return this;
        }

        public Builder newPassword(String newPassword){
            this.newPassword = newPassword;
            return this;
        }

        public PasswordUpdateRequest build(){
            return new PasswordUpdateRequest(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
