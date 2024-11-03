package com.example.demo.service;

import com.example.demo.domain.service.TokenService;
import com.example.demo.api.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;

@Service
public class AutenticacaoService {

    private final RestTemplate restTemplate;

    @Autowired
    public TokenService tokenService;

    @Autowired
    public AutenticacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RedirectView redirecionarParaLogin() {
        LoginDTO.Request.Base credenciais = new LoginDTO.Request.Base();


        String url = "https://accounts.spotify.com/authorize" +
                "?client_id=" + credenciais.getClientId() +
                "&response_type=" + credenciais.getResponseType() +
                "&redirect_uri=" + credenciais.getRedirectUri() +
                "&state=" + credenciais.getState() +
                "&scope=" + credenciais.getScope().replace(" ", "%20");

        System.out.println("URL de autenticação gerada: " + url);
        return new RedirectView(url);
    }

    public ResponseEntity<LoginDTO.Response.TokenResponse> obterToken(String codigo, String state) {
        LoginDTO.Request.Base credenciais = new LoginDTO.Request.Base();
        System.out.println("redirect uri: " + credenciais.getRedirectUri());

        System.out.println("Getstate: " + credenciais.getState());
        System.out.println("codigo: " + codigo);
        System.out.println("state pego do parametro: " + state);
        try {
            String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(
                    (credenciais.getClientId() + ":" + credenciais.getClientSecret()).getBytes());

            // Define os cabeçalhos da requisição
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);
            headers.set("Content-Type", "application/x-www-form-urlencoded");
            headers.set("Accept", "application/json");

            // Define o corpo da requisição
            String body = "grant_type=authorization_code" +
                    "&code=" + codigo +
                    "&redirect_uri=" + credenciais.getRedirectUri();

            // Cria a entidade de requisição
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            // Realiza a troca do código pelo token
            ResponseEntity<LoginDTO.Response.TokenResponse> responseEntity = restTemplate.exchange(
                    "https://accounts.spotify.com/api/token",
                    HttpMethod.POST,
                    requestEntity,
                    LoginDTO.Response.TokenResponse.class
            );
            //O body da resquisiçãao é uma instância de LoginDTO.Response.TokenResponse
            tokenService.salvarToken(responseEntity.getBody());
            System.out.println("O TOKEN É ESSE AQUI" + tokenService.obterToken());
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Retorna um status apropriado em caso de erro
        }
    }
}
