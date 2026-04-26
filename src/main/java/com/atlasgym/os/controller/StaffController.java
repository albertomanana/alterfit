package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.StaffPerfil;
import com.atlasgym.os.model.enums.AccessProfile;
import com.atlasgym.os.service.StaffService;
import com.atlasgym.os.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("staffList", staffService.findAll());
        model.addAttribute("activePage", "staff");
        model.addAttribute("title", "Plantilla de Staff");
        return "staff/list";
    }

    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("staff", new StaffPerfil());
        // Solo usuarios con perfil de staff que no tengan perfil ya creado (simplificado: todos los usuarios de staff)
        model.addAttribute("usuarios", usuarioService.findAll().stream()
                .filter(u -> u.getPerfil() != AccessProfile.CLIENTE)
                .toList());
        model.addAttribute("activePage", "staff");
        return "staff/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute StaffPerfil staff, RedirectAttributes ra) {
        staffService.save(staff);
        ra.addFlashAttribute("success", "Perfil de staff actualizado.");
        return "redirect:/staff";
    }
}
