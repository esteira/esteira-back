package com.example.demo.Model;

import org.springframework.stereotype.Component;

@Component
public class Credenciais {

    private String clientId = "c6ded4fe45494a83ad63bb28a654897b";
    private String clientSecret = "8f40feea75f24b2aa67513af23faed15";
    private String responseType = "code";
    private String redirectUri = "http://localhost:8080/api/user/callback";
    private String state = "state";
    private String scope = "user-read-private user-read-email user-library-read user-library-modify user-read-recently-played user-top-read user-read-playback-position user-follow-read user-follow-modify playlist-modify-public playlist-modify-private playlist-read-collaborative playlist-read-private streaming streaming user-read-currently-playing user-modify-playback-state user-read-playback-state ugc-image-upload";

    public String getClientId() {
        return clientId;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getState() {
        return state;
    }

    public String getScope() {
        return scope;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
