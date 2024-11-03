package com.example.demo.Controller;


import com.example.demo.Model.Services.PlaylistService;
import com.example.demo.domain.entity.Playlist;
import com.example.demo.domain.service.TokenService;
import com.example.demo.Model.Services.UsuarioService;
import com.example.demo.domain.entity.UserResponse;
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
    private final PlaylistService playlistService;

    public PlaylistCreator(TokenService tokenService, UsuarioService usuarioService, RestTemplate restTemplate, PlaylistService playlistService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.restTemplate = restTemplate;
        this.playlistService = playlistService;
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
    public Playlist geraPlaylistVazia() {


        String token = tokenService.obterToken();


        System.out.println("-------COMEÇA A GERAR PLAYLIST-------");

        try{
            String playlistData = "{ \"name\": \" playlist com Id\", \"description\": \" experimento 221 \", \"public\": true }";


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
            playlistService.salvarPlaylist(responseEntity.getBody());
            System.out.println(playlistService.getPlaylistId(responseEntity.getBody()));

            return responseEntity.getBody();
        } catch (Error e) {
            e.printStackTrace();
            return null;
        }

    }

}
