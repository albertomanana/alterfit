package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Gasto;
import com.atlasgym.os.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/gastos")
@RequiredArgsConstructor
public class GastoController {
    private final GastoService gastoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("gastos", gastoService.findAll());
        model.addAttribute("activePage", "finanzas");
        model.addAttribute("title", "Control de Gastos");
        return "gastos/list";
    }

    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("gasto", new Gasto());
        model.addAttribute("activePage", "finanzas");
        return "gastos/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Gasto gasto, RedirectAttributes ra) {
        if (gasto.getFecha() == null) gasto.setFecha(LocalDateTime.now());
        gastoService.save(gasto);
        ra.addFlashAttribute("success", "Gasto registrado correctamente.");
        return "redirect:/gastos";
    }
}
