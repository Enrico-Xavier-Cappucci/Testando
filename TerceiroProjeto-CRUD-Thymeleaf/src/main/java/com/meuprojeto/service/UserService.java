package com.meuprojeto.service;

import com.meuprojeto.model.User;
import com.meuprojeto.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Métodos EXISTENTES (mantidos intactos)
    public User registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN");
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    
    // Verifica se a senha é a padrão ("senha123")
    public boolean isDefaultPassword(User user) {
        return passwordEncoder.matches("senha123", user.getPassword());
    }

    // Atualiza usuário (para troca de senha)
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Novo método específico para registrar empregados
    public User registerNewEmployee(User empregado) {
        empregado.setPassword(passwordEncoder.encode("senha123")); // Senha padrão
        empregado.setRole("EMPREGADO");
        empregado.setPasswordChanged(false); // Força troca no primeiro login
        return userRepository.save(empregado);
    }
}
