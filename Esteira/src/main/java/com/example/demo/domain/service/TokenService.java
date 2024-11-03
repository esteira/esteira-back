package com.example.demo.domain.service;

import com.example.demo.api.dto.LoginDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class TokenService {

    public TokenService(@Lazy LoginDTO.Response.TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
    }

    private LoginDTO.Response.TokenResponse tokenResponse;

    public void salvarToken(LoginDTO.Response.TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
        System.out.println("Token salvo com sucesso! O token é esse aqui: " + tokenResponse.getAccessToken());
    }

    public String obterToken() {
        return tokenResponse.getAccessToken();
    }

    public boolean isTokenValido() {
        return tokenResponse != null && tokenResponse.getAccessToken() != null;
    }
}
