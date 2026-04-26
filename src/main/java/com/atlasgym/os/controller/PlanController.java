package com.atlasgym.os.controller;

import com.atlasgym.os.model.entity.Plan;
import com.atlasgym.os.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("planes", planService.findAll());
        model.addAttribute("activePage", "membresias");
        model.addAttribute("title", "Catálogo de Planes");
        return "planes/list";
    }

    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("plan", new Plan());
        model.addAttribute("activePage", "membresias");
        return "planes/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Plan plan, RedirectAttributes ra) {
        planService.save(plan);
        ra.addFlashAttribute("success", "Plan actualizado correctamente.");
        return "redirect:/planes";
    }
}
