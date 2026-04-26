package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Rutina;
import com.atlasgym.os.service.RutinaService;
import com.atlasgym.os.service.StaffService;
import com.atlasgym.os.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rutinas")
@RequiredArgsConstructor
public class RutinaController {
    private final RutinaService rutinaService;
    private final UsuarioService usuarioService;
    private final StaffService staffService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rutinas", rutinaService.findAll());
        model.addAttribute("activePage", "rutinas");
        model.addAttribute("title", "Biblioteca de Rutinas");
        return "rutinas/list";
    }

    @GetMapping("/nueva")
    public String form(Model model) {
        model.addAttribute("rutina", new Rutina());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("staffList", staffService.findAll());
        model.addAttribute("activePage", "rutinas");
        return "rutinas/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Rutina rutina, RedirectAttributes ra) {
        rutinaService.save(rutina);
        ra.addFlashAttribute("success", "Rutina guardada correctamente.");
        return "redirect:/rutinas";
    }
}
