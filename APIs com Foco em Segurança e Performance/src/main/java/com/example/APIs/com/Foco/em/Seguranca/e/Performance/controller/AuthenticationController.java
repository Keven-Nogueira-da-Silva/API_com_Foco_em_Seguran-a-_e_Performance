package com.example.APIs.com.Foco.em.Seguranca.e.Performance.controller;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.AuthenticationDTO;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.LoginResponseDTO;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.RegisterDTO;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Usuario;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.repository.UsuarioRepository;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(null, data.login(), encryptedPassword, data.role(), null);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
