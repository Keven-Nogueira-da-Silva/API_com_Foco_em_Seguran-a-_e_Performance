package com.example.APIs.com.Foco.em.Seguranca.e.Performance.repository;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.TransacaoResumoDTO;
import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


    @Query("""
    SELECT new com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto.TransacaoResumoDTO(
        t.id, 
        t.valor, 
        t.data, 
        t.status, 
        u.login
    )
    FROM Transacao t
    JOIN t.usuario u
    WHERE t.status = :status
    AND (:usuarioId IS NULL OR u.id = :usuarioId)
    """)
    Page<TransacaoResumoDTO> buscarTransacoesCustom(
            @Param("status") String status,
            @Param("usuarioId") Long usuarioId,
            Pageable pageable
    );
}
