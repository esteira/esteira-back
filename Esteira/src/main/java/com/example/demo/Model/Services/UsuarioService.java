package com.example.demo.Model.Services;

import com.example.demo.domain.entity.UserResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import lombok.Data;

@Service
public class UsuarioService {

    private UserResponse userResponse;


    public UsuarioService(@Lazy UserResponse userResponse) {
        this.userResponse = userResponse;
    }




    public void salvarUser(UserResponse userResponse) {
        this.userResponse = userResponse;
        System.out.println("Usuário salvo com sucesso!" + userResponse.getSpotifyId());
    }



    public UserResponse getUsuario() {
        return userResponse;
    }

    public String getSpotifyId() {
        return userResponse.getSpotifyId();
    }
}
