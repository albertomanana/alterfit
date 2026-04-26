package com.atlasgym.os.controller;

import com.atlasgym.os.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final AnalyticsService analyticsService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("stats", analyticsService.getDashboardStats());
        model.addAttribute("title", "Dashboard");
        model.addAttribute("activePage", "dashboard");
        return "dashboard/index";
    }
}
