package com.meuprojeto.controller;

import com.meuprojeto.model.Empregado;
import com.meuprojeto.model.Tarefa;
import com.meuprojeto.service.EmpregadoService;
import com.meuprojeto.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empregado")
public class EmpregadoController {
    
    @Autowired
    private EmpregadoService empregadoService;
    
    @Autowired
    private TarefaService tarefaService;
    
    @GetMapping("/login")
    public String loginEmpregado() {
        return "login-empregado";
    }
    
    @GetMapping("/dashboard")
    public String dashboardEmpregado(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Optional<Empregado> empregadoOpt = empregadoService.findByEmail(email);
        if (empregadoOpt.isPresent()) {
            Empregado empregado = empregadoOpt.get();
            
            // Se é o primeiro login, redirecionar para troca de senha
            if (empregado.isPrimeiroLogin()) {
                return "redirect:/empregado/trocar-senha";
            }
            
            List<Tarefa> tarefas = tarefaService.getTarefasByEmpregado(empregado);
            model.addAttribute("tarefas", tarefas);
            model.addAttribute("empregado", empregado);
            return "dashboard-empregado";
        }
        
        return "redirect:/empregado/login?error=true";
    }
    
    @GetMapping("/trocar-senha")
    public String trocarSenhaForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("email", email);
        return "trocar-senha-empregado";
    }
    
    @PostMapping("/trocar-senha")
    public String trocarSenha(@RequestParam String novaSenha, 
                             @RequestParam String confirmarSenha,
                             RedirectAttributes redirectAttributes) {
        
        if (novaSenha.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "A senha deve ter pelo menos 6 caracteres.");
            return "redirect:/empregado/trocar-senha";
        }
        
        if (!novaSenha.equals(confirmarSenha)) {
            redirectAttributes.addFlashAttribute("error", "As senhas não coincidem.");
            return "redirect:/empregado/trocar-senha";
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        try {
            empregadoService.trocarSenha(email, novaSenha);
            redirectAttributes.addFlashAttribute("success", "Senha alterada com sucesso!");
            return "redirect:/empregado/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao alterar senha: " + e.getMessage());
            return "redirect:/empregado/trocar-senha";
        }
    }
    
    @PostMapping("/marcar-tarefa/{id}")
    public String marcarTarefa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            tarefaService.marcarComoConcluida(id);
            redirectAttributes.addFlashAttribute("success", "Status da tarefa atualizado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar tarefa: " + e.getMessage());
        }
        return "redirect:/empregado/dashboard";
    }
}

