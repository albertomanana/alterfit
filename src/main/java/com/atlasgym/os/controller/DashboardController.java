package com.atlasgym.os.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("activePage", "dashboard");
        return "dashboard/index";
    }
}
