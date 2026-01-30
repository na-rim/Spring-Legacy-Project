package org.example.naco.controller;

import org.example.naco.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Simple admin page... useful for demonstrating Role-based authorization.
 * Protected by AdminInterceptor (mapped to /admin/**).
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @PostMapping("/users/{id}/role")
    public String changeRole(@PathVariable Long id, @RequestParam String role) {
        userService.changeRole(id, role);
        return "redirect:/admin/users";
    }
}
