package com.example.APIs.com.Foco.em.Seguranca.e.Performance.controller;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.TransacaoResumoDTO;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Usuario;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transacoes") // Esta é a rota que você chamou no Postman!
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<Page<TransacaoResumoDTO>> listarPorStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal Usuario usuarioLogado) { // O Spring pega o dono do Token aqui!

        Page<TransacaoResumoDTO> transacoes = transacaoService.listarTransacoesPorStatus(status, page, size, usuarioLogado);
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping
    public ResponseEntity<TransacaoResumoDTO> cadastrar(
            @RequestBody @Valid TransacaoResumoDTO dados, // O @Valid ativa a verificação!
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        var dto = transacaoService.salvar(dados, usuarioLogado);
        return ResponseEntity.ok(dto);
    }

}
