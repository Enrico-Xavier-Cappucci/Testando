package com.meuprojeto.controller;

import com.meuprojeto.model.Admin;
import com.meuprojeto.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login-admin")
    public String login() {
        return "login-admin";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("admin", new Admin());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("admin") Admin admin, RedirectAttributes redirectAttributes) {
        try {
            // Verificar se o usuário/admin já existe
            if (adminService.findByUsername(admin.getUsername()) != null) {
                redirectAttributes.addFlashAttribute("error", "Nome de usuário já existe!");
                return "redirect:/registration";
            }

            adminService.registerNewAdmin(admin);
            redirectAttributes.addFlashAttribute("success", "Usuário registrado com sucesso! Faça login.");
            return "redirect:/login-admin";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao registrar usuário: " + e.getMessage());
            return "redirect:/registration";
        }
    }
}

