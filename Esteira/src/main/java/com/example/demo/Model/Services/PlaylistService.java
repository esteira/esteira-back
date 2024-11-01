package com.example.demo.Model.Services;

import com.example.demo.Controller.Playlist;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class PlaylistService {

    private Playlist playlist;

    public PlaylistService(Playlist playlist) {
        this.playlist = playlist;
    }

    public void salvarPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public String getPlaylistId(Playlist playlist) {
        return playlist.getSpotifyId();
    }
}


