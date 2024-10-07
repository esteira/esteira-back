package com.example.demo.Client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    public LoginResponse() {
    }

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
