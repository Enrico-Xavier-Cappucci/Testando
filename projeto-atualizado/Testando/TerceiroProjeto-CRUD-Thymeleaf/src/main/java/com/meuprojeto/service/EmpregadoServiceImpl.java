package com.meuprojeto.service;

import com.meuprojeto.model.Empregado;
import com.meuprojeto.repositorio.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmpregadoServiceImpl implements EmpregadoService {
    
    @Autowired
    private EmpregadoRepository empregadoRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Optional<Empregado> findByEmail(String email) {
        return empregadoRepository.findByEmail(email);
    }
    
    @Override
    public Empregado salvarEmpregado(Empregado empregado) {
        // Se é um novo empregado, criptografar a senha padrão
        if (empregado.getId() == null) {
            empregado.setSenha(passwordEncoder.encode("senha123"));
            empregado.setPrimeiroLogin(true);
        }
        return empregadoRepository.save(empregado);
    }
    
    @Override
    public void trocarSenha(String email, String novaSenha) {
        Optional<Empregado> empregadoOpt = empregadoRepository.findByEmail(email);
        if (empregadoOpt.isPresent()) {
            Empregado empregado = empregadoOpt.get();
            empregado.setSenha(passwordEncoder.encode(novaSenha));
            empregado.setPrimeiroLogin(false);
            empregadoRepository.save(empregado);
        } else {
            throw new RuntimeException("Empregado não encontrado com email: " + email);
        }
    }
    
    @Override
    public boolean validarCredenciais(String email, String senha) {
        Optional<Empregado> empregadoOpt = empregadoRepository.findByEmail(email);
        if (empregadoOpt.isPresent()) {
            Empregado empregado = empregadoOpt.get();
            return passwordEncoder.matches(senha, empregado.getSenha());
        }
        return false;
    }
}

