package com.meuprojeto.controller;

import com.meuprojeto.model.User;
import com.meuprojeto.service.UserService;
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
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            // Verificar se o usuário já existe
            if (userService.findByUsername(user.getUsername()) != null) {
                redirectAttributes.addFlashAttribute("error", "Nome de usuário já existe!");
                return "redirect:/registration";
            }

            userService.registerNewUser(user);
            redirectAttributes.addFlashAttribute("success", "Usuário registrado com sucesso! Faça login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao registrar usuário: " + e.getMessage());
            return "redirect:/registration";
        }
    }
}

