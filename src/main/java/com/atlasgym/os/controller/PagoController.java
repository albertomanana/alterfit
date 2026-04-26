package com.atlasgym.os.controller;

import com.atlasgym.os.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("pagos", pagoService.findAll());
        model.addAttribute("activePage", "pagos");
        model.addAttribute("title", "Registro de Pagos");
        return "pagos/list";
    }

    @PostMapping("/{id}/pagar")
    public String pagar(@PathVariable Long id, RedirectAttributes ra) {
        pagoService.marcarComoPagado(id);
        ra.addFlashAttribute("success", "Pago registrado correctamente.");
        return "redirect:/pagos";
    }
}
