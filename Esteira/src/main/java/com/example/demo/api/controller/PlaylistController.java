package com.example.demo.api.controller;


import com.example.demo.domain.entity.Track;
import com.example.demo.domain.service.PlaylistService;
import com.example.demo.domain.entity.Playlist;
import com.example.demo.domain.entity.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/pegaId")
    public ResponseEntity<UserResponse> pegaId() {
        UserResponse user = playlistService.pegarIdDoUsuario();
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(500).build(); // Resposta caso ocorra um erro
        }
    }

    @GetMapping("/criaPlaylistComTracks")
    public ResponseEntity<Playlist> geraPlaylistComTracks(
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam("seed_genres") String seedGenres,
            @RequestParam("min_tempo") int minTempo,
            @RequestParam("max_tempo") int maxTempo) {

        // 1. Cria a playlist vazia
        Playlist playlist = playlistService.gerarPlaylistVazia();
        // 2. Obter recomendações de faixas
       List<Track> tracks = playlistService.obterRecomendacoes(limit, seedGenres, minTempo, maxTempo);
        // 3. Adicionar as faixas recomendadas à playlist
        boolean sucesso =  playlistService.adicionarFaixasAPlaylist(playlist.getSpotifyId(), tracks);
        if (sucesso) {
            return ResponseEntity.ok(playlist);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}



