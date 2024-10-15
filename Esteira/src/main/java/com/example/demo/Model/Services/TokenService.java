package com.example.demo.Model.Services;

import com.example.demo.Model.TokenResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class TokenService {

    public TokenService(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
    }

    private TokenResponse tokenResponse;

    public void salvarToken(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
        System.out.println("Token salvo com sucesso!" + tokenResponse);
    }

    public String obterToken() {
        return tokenResponse.getAccessToken();
    }

    public boolean isTokenValido() {
        return tokenResponse != null && tokenResponse.getAccessToken() != null;
    }
}
