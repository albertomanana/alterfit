package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.service.AsistenciaService;
import com.atlasgym.os.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {
    private final AsistenciaService asistenciaService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("asistencias", asistenciaService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("activePage", "asistencias");
        model.addAttribute("title", "Control de Asistencias");
        return "asistencias/list";
    }

    @PostMapping("/checkin")
    public String checkin(@RequestParam Long usuarioId, RedirectAttributes ra) {
        usuarioService.findById(usuarioId).ifPresent(u -> {
            asistenciaService.registrarCheckIn(u);
            ra.addFlashAttribute("success", "Check-in realizado para: " + u.getNombreCompleto());
        });
        return "redirect:/asistencias";
    }
}
