package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Nomina;
import com.atlasgym.os.service.NominaService;
import com.atlasgym.os.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

@Controller
@RequestMapping("/nominas")
@RequiredArgsConstructor
public class NominaController {
    private final NominaService nominaService;
    private final StaffService staffService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("nominas", nominaService.findAll());
        model.addAttribute("activePage", "staff");
        model.addAttribute("title", "Gestión de Nóminas");
        return "nominas/list";
    }

    @GetMapping("/nueva")
    public String form(Model model) {
        model.addAttribute("nomina", new Nomina());
        model.addAttribute("staffList", staffService.findAll());
        model.addAttribute("activePage", "staff");
        return "nominas/form";
    }

    @PostMapping("/emitir")
    public String emitir(@ModelAttribute Nomina nomina, RedirectAttributes ra) {
        if (nomina.getFechaEmision() == null) nomina.setFechaEmision(LocalDate.now());
        nominaService.emitirNomina(nomina);
        ra.addFlashAttribute("success", "Nómina emitida y gasto asociado generado.");
        return "redirect:/nominas";
    }
}
