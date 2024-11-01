package com.example.demo.Controller;

import com.example.demo.Model.Credenciais;
import com.example.demo.Model.Services.TokenService;
import com.example.demo.Model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class AutenticacaoController {

    private final RestTemplate restTemplate;
    private final Credenciais credenciais;
    private final TokenService tokenService;

    @Autowired
    public AutenticacaoController(RestTemplate restTemplate, Credenciais credenciais, TokenService tokenService) {
        this.restTemplate = restTemplate;
        this.credenciais = credenciais;
        this.tokenService = tokenService;
    }

    @GetMapping("/login")
    public RedirectView logar() {

        System.out.println("JOao fez login + 1");
        // Montando a URL de autorização do Spotify
        String url = "https://accounts.spotify.com/authorize" +
                "?client_id=" + credenciais.getClientId() +
                "&response_type=" + credenciais.getResponseType() +
                "&redirect_uri=" + credenciais.getRedirectUri() +
                "&state=" + credenciais.getState() +
                "&scope=" + credenciais.getScope();

        // Redireciona o usuário para a URL de login do Spotify
        return new RedirectView(url);
    }


    @GetMapping("/api/user/callback")
    public TokenResponse PegaToken(@RequestParam("code") String codigo, @RequestParam("state") String state) {
        // Cria o Authorization Header dinâmico com o Base64 codificado das credenciais
        System.out.println("---------------------Método PegaToken chamado---------------------");


        try {
            String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(
                    (credenciais.getClientId() + ":" + credenciais.getClientSecret()).getBytes());


            // Criação dos headers da requisição
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            headers.set("Accept", "application/json");


            // Monta os parâmetros da requisição
            String body = "grant_type=authorization_code" +
                    "&code=" + codigo +
                    "&redirect_uri=" + credenciais.getRedirectUri();

            // Cria a entidade da requisição
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            // Faz a requisição para trocar o código por um token
            ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                    "https://accounts.spotify.com/api/token",
                    HttpMethod.POST,
                    requestEntity,
                    TokenResponse.class
            );
            tokenService.salvarToken(responseEntity.getBody());
            System.out.println("O TOKEN É ESSE AQUI" + tokenService.obterToken());

            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace da exceção
            return null; // Ou uma resposta adequada em caso de erro
        }
    }
}
