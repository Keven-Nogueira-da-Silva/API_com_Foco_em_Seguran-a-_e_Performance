package com.example.APIs.com.Foco.em.Seguranca.e.Performance.repository;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // O Spring Security usa esse método para encontrar o usuário no banco
    // Retornamos UserDetails para integração direta com o framework de segurança
    UserDetails findByLogin(String login);
}
