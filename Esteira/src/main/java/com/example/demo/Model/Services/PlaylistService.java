package com.example.demo.Model.Services;

import com.example.demo.domain.entity.Playlist;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private Playlist playlist;

    public PlaylistService(@Lazy Playlist playlist) {
        this.playlist = playlist;
    }

    public void salvarPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getPlaylistId(Playlist playlist) {
        return playlist.getSpotifyId();
    }
}


