package com.example.APIs.com.Foco.em.Seguranca.e.Performance.service;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.TransacaoResumoDTO;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Transacao;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.UserRole;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Usuario;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository repository;


    @Transactional
            (readOnly = true) // Otimiza a performance ignorando o dirty checking do Hibernate
    public Page<TransacaoResumoDTO> listarTransacoesPorStatus(String status, int page, int size, Usuario usuarioLogado) {
        Pageable pageable = PageRequest.of(page, size);

        // Se for ADMIN, o filtro de ID é nulo (vê tudo)
        // Se não for ADMIN, usamos o ID do usuário que veio do Token
        Long filtroUsuarioId = usuarioLogado.getRole().equals(UserRole.ADMIN) ? null : usuarioLogado.getId();

        return repository.buscarTransacoesCustom(status, filtroUsuarioId, pageable);
    }

    public TransacaoResumoDTO salvar(TransacaoResumoDTO dados, Usuario usuarioLogado) {
        Transacao novaTransacao = new Transacao(dados);
        novaTransacao.setUsuario(usuarioLogado);

        // ADICIONE ESTA LINHA ABAIXO para gerar a data automaticamente:
        novaTransacao.setData(LocalDateTime.now());

        repository.save(novaTransacao);
        return new TransacaoResumoDTO(novaTransacao);
    }


}
