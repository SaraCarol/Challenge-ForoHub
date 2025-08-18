package com.alura.foroHub.controller;

import com.alura.foroHub.entity.Usuario;
import com.alura.foroHub.entradaDTO.AutenticacionDTO;
import com.alura.foroHub.salidaDTO.TokenDTO;
import com.alura.foroHub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid AutenticacionDTO autenticacionDTO){
        var authToken = new UsernamePasswordAuthenticationToken(autenticacionDTO.email(), autenticacionDTO.password());
        var autorizacion = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((Usuario) autorizacion.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    };

}
