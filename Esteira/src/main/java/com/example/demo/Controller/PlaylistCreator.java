package com.example.demo.Controller;


import com.example.demo.Model.Services.TokenService;
import com.example.demo.Model.Services.UsuarioService;
import com.example.demo.Model.UserResponse;
import jdk.jfr.ContentType;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PlaylistCreator {


    private UserResponse usuario;
    private final TokenService tokenService;
    private UsuarioService usuarioService;
    private final RestTemplate restTemplate;

    public PlaylistCreator(TokenService tokenService, UsuarioService usuarioService, RestTemplate restTemplate) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.restTemplate = restTemplate;

    }

    @GetMapping("/pegaId")
    public UserResponse pegaId() {

        String token = tokenService.obterToken();

        System.out.println("-------DADOS DO TOKEN-------");

        try{

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Accept", "application/json");


        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(
                "https://api.spotify.com/v1/me",
                HttpMethod.GET,
                requestEntity,
                UserResponse.class
        );
        usuarioService.salvarUser(responseEntity.getBody());
            System.out.println("usuario id " + usuarioService.getSpotifyId());
            System.out.println(token);
        return usuario;} catch (Error e) {
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/criaPlaylist")
    public Playlist geraPlaylist() {

        System.out.println(tokenService.obterToken());
        String token = tokenService.obterToken();
        System.out.println(token);

        System.out.println("-------COMEÇA A GERAR PLAYLIST-------");

        try{
            String playlistData = "{ \"name\": \"nova playlist vazia\", \"description\": \"primeira playlist a ser populada por puredo\", \"public\": true }";


            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");


            HttpEntity<String> requestEntity = new HttpEntity<>(playlistData,headers);


            ResponseEntity<Playlist> responseEntity = restTemplate.exchange(
                    "https://api.spotify.com/v1/users/" + usuarioService.getSpotifyId() + "/playlists",
                    HttpMethod.POST,
                    requestEntity,
                    Playlist.class
            );

            System.out.println("playlist criada e vazia!");
            return responseEntity.getBody();
        } catch (Error e) {
            e.printStackTrace();
            return null;
        }

    }
}
