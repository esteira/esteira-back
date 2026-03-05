package com.example.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;


public enum LoginDTO {;

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    // Interface para cada parâmetro de credencial
    private interface ClientId {
         String getClientId();
    }

    private interface ClientSecret {
        String getClientSecret();
    }

    private interface Scope {
      String getScope();
    }

    private interface RedirectUri {
       String getRedirectUri();
    }

    private interface State {
        String getState();
    }

    private interface ResponseType {
         String getResponseType();
    }

    // DTO para request de login, implementando as interfaces das credenciais
    public enum Request {;

        @Data
        public static class Base implements ClientId, ClientSecret, Scope, RedirectUri, State, ResponseType {
            private final String clientId = dotenv.get("SPOTIFY_CLIENT_ID");
            private final String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET");
            private final String responseType = dotenv.get("SPOTIFY_RESPONSE_TYPE");
            private final String redirectUri = dotenv.get("SPOTIFY_REDIRECT_URI");
            private final String state = dotenv.get("SPOTIFY_STATE");
            private final String scope = dotenv.get("SPOTIFY_SCOPE");
        }
    }


    public enum Response {;
        @Data
        public static class TokenResponse {

            @JsonProperty("access_token")
            private String accessToken;

            @JsonProperty("token_type")
            private String tokenType;

            @JsonProperty("scope")
            private String scope;

            @JsonProperty("expires_in")
            private Integer expiresIn;

            @JsonProperty("refresh_token")
            private String refreshToken;
        }
    }
}
