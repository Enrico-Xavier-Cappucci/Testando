package com.meuprojeto.service;

import com.meuprojeto.model.Admin;
import com.meuprojeto.repositorio.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerNewAdmin(Admin admin) {
    	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    	admin.setRole("ADMIN"); // Definindo o papel como ADMIN para o primeiro usuário
        return adminRepository.save(admin);
    }

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}

