package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.service.AccessSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@RequiredArgsConstructor
public class ShellViewAdvice {

    private final AccessSessionService accessSessionService;

    @ModelAttribute("usuarioActual")
    public Usuario getUsuarioActual() {
        return accessSessionService.getCurrentUser().orElse(null);
    }

    @ModelAttribute("perfilActual")
    public String getPerfilActual() {
        return accessSessionService.getCurrentUser()
                .map(u -> u.getPerfil().getDisplay())
                .orElse(null);
    }

    @ModelAttribute("fechaOperativa")
    public String getFechaOperativa() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
