package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.MembresiaUsuario;
import com.atlasgym.os.service.MembresiaService;
import com.atlasgym.os.service.PlanService;
import com.atlasgym.os.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/membresias")
@RequiredArgsConstructor
public class MembresiaController {
    private final MembresiaService membresiaService;
    private final UsuarioService usuarioService;
    private final PlanService planService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("membresias", membresiaService.findAll());
        model.addAttribute("activePage", "membresias");
        model.addAttribute("title", "Contratos de Membresía");
        return "membresias/list";
    }

    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("membresia", new MembresiaUsuario());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("planes", planService.findActivos());
        model.addAttribute("activePage", "membresias");
        return "membresias/form";
    }

    @PostMapping("/contratar")
    public String contratar(@ModelAttribute MembresiaUsuario membresia, RedirectAttributes ra) {
        membresiaService.contratarPlan(membresia);
        ra.addFlashAttribute("success", "Membresía activada y pago generado.");
        return "redirect:/membresias";
    }
}
