package com.atlasgym.os.controller;

import com.atlasgym.os.service.AccessSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AccesoController {

    private final AccessSessionService accessSessionService;

    @GetMapping("/acceso")
    public String showLogin(Model model) {
        if (accessSessionService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("title", "Acceso");
        return "acceso/login";
    }

    @PostMapping("/acceso")
    public String login(@RequestParam String identifier, 
                        @RequestParam String password, 
                        RedirectAttributes redirectAttributes) {
        if (accessSessionService.login(identifier, password)) {
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas o cuenta desactivada.");
        return "redirect:/acceso";
    }

    @PostMapping("/salir")
    public String logout() {
        accessSessionService.logout();
        return "redirect:/acceso";
    }
}
