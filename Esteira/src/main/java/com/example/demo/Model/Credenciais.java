
package com.example.demo.Model;

import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class Credenciais {
    private final Dotenv dotenv = Dotenv.configure()
            .directory("C:/Users/Jayro/Downloads/Programação/Esteira/Esteira/.env")
            .load();

    private String clientId = dotenv.get("SPOTIFY_CLIENT_ID");
    private String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET");
    private String responseType = dotenv.get("SPOTIFY_RESPONSE_TYPE");
    private String redirectUri = dotenv.get("SPOTIFY_REDIRECT_URI");
    private String state = dotenv.get("SPOTIFY_STATE");
    private String scope = dotenv.get("SPOTIFY_SCOPE");


    public String getClientId() {
        return this.clientId;
    }

    public String getResponseType() {
        return this.responseType;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public String getState() {
        return this.state;
    }

    public String getScope() {
        return this.scope;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

}

