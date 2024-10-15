package com.example.demo.Model.Services;

import com.example.demo.Model.UserResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class UsuarioService {

    private UserResponse userResponse;


    public UsuarioService(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public void salvarUser(UserResponse userResponse) {
        this.userResponse = userResponse;
        System.out.println("Token salvo com sucesso!" + userResponse.getspotifyId());
    }



    public UserResponse getUsuario() {
        return userResponse;
    }

    public String getSpotifyId() {
        return userResponse.getspotifyId();
    }
}
