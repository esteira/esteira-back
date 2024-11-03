package com.example.demo.Controller;

import com.example.demo.api.dto.LoginDTO;
import com.example.demo.service.AutenticacaoService;
import org.bouncycastle.jcajce.provider.asymmetric.ec.GMSignatureSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @Autowired
    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }


    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView logar() {
        return autenticacaoService.redirecionarParaLogin();
    }


    @GetMapping(value = "/api/user/callback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDTO.Response.TokenResponse> obterToken(
       @RequestParam("code") String codigo,
       @RequestParam("state") String state) {

        return autenticacaoService.obterToken(codigo, state);
    }
}
