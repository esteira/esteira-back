package com.example.demo.Controller;

import com.example.demo.Client.AuthSpotifyClient;
import com.example.demo.Client.GoogleClient;
import com.example.demo.Client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify/api")
public class PlaylistController {

    private final AuthSpotifyClient authSpotifyClient;

    public PlaylistController(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
        LoginRequest request = new LoginRequest(
                "client_credentials",
                 "c6ded4fe45494a83ad63bb28a654897b",
                 "8f40feea75f24b2aa67513af23faed15"

        );

      return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }

}

