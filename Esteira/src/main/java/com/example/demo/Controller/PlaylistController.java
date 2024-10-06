package com.example.demo.Controller;

import com.example.demo.Client.GoogleClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify/api")
public class PlaylistController {

    private final GoogleClient googleClient;

    public PlaylistController(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
      return ResponseEntity.ok(googleClient.helloWorld());
    }

}

