package com.example.APIs.com.Foco.em.Seguranca.e.Performance.dto;

import com.example.APIs.com.Foco.em.Seguranca.e.Performance.model.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {}
