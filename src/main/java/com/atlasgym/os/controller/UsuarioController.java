package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.model.enums.AccessProfile;
import com.atlasgym.os.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("title", "Gestión de Usuarios");
        model.addAttribute("activePage", "usuarios");
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("perfiles", AccessProfile.values());
        model.addAttribute("title", "Nuevo Usuario");
        model.addAttribute("activePage", "usuarios");
        return "usuarios/form";
    }

    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        return usuarioService.findById(id).map(u -> {
            model.addAttribute("usuario", u);
            model.addAttribute("perfiles", AccessProfile.values());
            model.addAttribute("title", "Editar Usuario");
            model.addAttribute("activePage", "usuarios");
            return "usuarios/form";
        }).orElse("redirect:/usuarios");
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.save(usuario);
        redirectAttributes.addFlashAttribute("success", "Usuario guardado correctamente.");
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.toggleActivo(id);
        redirectAttributes.addFlashAttribute("success", "Estado del usuario actualizado.");
        return "redirect:/usuarios";
    }
}
