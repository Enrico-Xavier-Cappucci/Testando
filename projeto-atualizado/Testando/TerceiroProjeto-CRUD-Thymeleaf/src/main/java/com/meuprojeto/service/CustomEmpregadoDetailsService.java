package com.meuprojeto.service;

import com.meuprojeto.model.Empregado;
import com.meuprojeto.repositorio.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service("empregadoDetailsService")
public class CustomEmpregadoDetailsService implements UserDetailsService {
    
    @Autowired
    private EmpregadoRepository empregadoRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Empregado> empregadoOpt = empregadoRepository.findByEmail(email);
        
        if (empregadoOpt.isEmpty()) {
            throw new UsernameNotFoundException("Empregado não encontrado com email: " + email);
        }
        
        Empregado empregado = empregadoOpt.get();
        
        return new User(
            empregado.getEmail(),
            empregado.getSenha(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPREGADO"))
        );
    }
}

