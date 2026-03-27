package com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Transacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
public class TransacaoResumoDTO {
    private Long id;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    private LocalDateTime data;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    private String usuarioNome;

    // ESTE É O CONSTRUTOR QUE A QUERY PRECISA (5 parâmetros na ordem correta)
    public TransacaoResumoDTO(Long id, BigDecimal valor, LocalDateTime data, String status, String usuarioNome) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.status = status;
        this.usuarioNome = usuarioNome;
    }

    // Mantenha o construtor que recebe a entidade (para o método salvar)
    public TransacaoResumoDTO(Transacao transacao) {
        this.id = transacao.getId();
        this.valor = transacao.getValor();
        this.data = transacao.getData();
        this.status = transacao.getStatus();
        this.usuarioNome = transacao.getUsuario().getLogin();
    }

    public TransacaoResumoDTO() {
    }

    // Não esqueça dos Getters se não for um Record!
}
