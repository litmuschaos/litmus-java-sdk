package io.litmuschaos.response;

import java.util.List;

public class ListTokensResponse {
    private List<TokenResponse> apiTokens;

    public ListTokensResponse(List<TokenResponse> tokens) {
        this.apiTokens = tokens;
    }

    public List<TokenResponse> getTokens() {
        return apiTokens;
    }

}
