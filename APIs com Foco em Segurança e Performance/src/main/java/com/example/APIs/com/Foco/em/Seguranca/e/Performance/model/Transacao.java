package com.example.APIs.com.Foco.em.Seguranca.e.Performance.model;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.TransacaoResumoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacoes", indexes = {
        @Index(name = "idx_usuario_data", columnList = "usuario_id, data_transacao")
})
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime data;

    @PrePersist
    protected void onCreate() {
        if (this.data == null) {
            this.data = LocalDateTime.now();
        }
    }
    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy para não carregar o usuário sem necessidade
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    // Dentro da classe Transacao
    public Transacao(TransacaoResumoDTO dados) {
        this.valor = dados.getValor();
        this.status = dados.getStatus();
        this.data = dados.getData(); // Certifique-se que o nome do campo é 'data'
    }
    public Transacao() {}
}
