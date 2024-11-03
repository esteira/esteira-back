package com.example.demo.domain.service;


import com.example.demo.domain.entity.Playlist;
import com.example.demo.domain.entity.Track;
import com.example.demo.domain.entity.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Lazy
@Service
public class PlaylistService {

    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final PlaylistService playlistService;
    private final RestTemplate restTemplate;
    private Playlist playlist;

    @Lazy
    @Autowired
    public PlaylistService(TokenService tokenService, UsuarioService usuarioService, RestTemplate restTemplate, PlaylistService playlistService, Playlist playlist) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.restTemplate = restTemplate;
        this.playlistService = playlistService;
        this.playlist = playlist;
    }

    public UserResponse pegarIdDoUsuario() {
        String token = tokenService.obterToken();
        try {
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
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Playlist gerarPlaylistVazia() {
        String token = tokenService.obterToken();

        try {
            String playlistData = "{ \"name\": \" primeira Playlist populada\", \"description\": \" Que massa bicho\", \"public\": true }";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");

            HttpEntity<String> requestEntity = new HttpEntity<>(playlistData, headers);
            ResponseEntity<Playlist> responseEntity = restTemplate.exchange(
                    "https://api.spotify.com/v1/users/" + usuarioService.getSpotifyId() + "/playlists",
                    HttpMethod.POST,
                    requestEntity,
                    Playlist.class
            );
            playlistService.salvarPlaylist(responseEntity.getBody());
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void salvarPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getPlaylistId(Playlist playlist) {
        return playlist.getSpotifyId();
}

    public List<Track> obterRecomendacoes(int limit, String seedGenres, int minTempo, int maxTempo) {
        String token = tokenService.obterToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/json");

        String url = UriComponentsBuilder.fromHttpUrl("https://api.spotify.com/v1/recommendations")
                .queryParam("limit", 10)
                .queryParam("seed_genres", "blues,indie,postpunk")
                .queryParam("min_tempo", 120)
                .queryParam("max_tempo", 140)
                .toUriString();

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);

        List<Map<String, Object>> tracksData = (List<Map<String, Object>>) response.getBody().get("tracks");

        return tracksData.stream().map(this::mapToTrack).collect(Collectors.toList());
    }

    public boolean adicionarFaixasAPlaylist(String playlistId, List<Track> tracks) {
        String token = tokenService.obterToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");

        // Converte a lista de Tracks para URIs
        List<String> uris = tracks.stream()
                .map(track -> "spotify:track:" + track.getTrackId())
                .collect(Collectors.toList());

        // Verifica se a lista de URIs não está vazia
        if (uris.isEmpty()) {
            System.out.println("A lista de URIs está vazia.");
            return false;
        }

        try {
            // Constrói o JSON da requisição usando ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(Map.of("uris", uris, "position", 0));

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }



}
    private Track mapToTrack(Map<String, Object> trackData) {
        Track track = new Track();
        track.setTrackId((String) trackData.get("id"));
        track.setName((String) trackData.get("name"));
        track.setUri((String) trackData.get("uri"));
        return track;
    }}
