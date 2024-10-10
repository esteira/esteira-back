package com.example.demo.Client;

import com.example.demo.Model.TokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( name = "PegadorDeAutorizacao",
                url = "https://accounts.spotify.com")
public interface AutorizacaoClient {

    @PostMapping("/api/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    TokenResponse trocarCodigoPorToken(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestParam("grant_type") String grantType,  // sempre será "authorization_code"
                                       @RequestParam("code") String code,            // o código de autorização recebido
                                       @RequestParam("redirect_uri") String redirectUri); // o mesmo redirect_uri usado anteriormente

}
