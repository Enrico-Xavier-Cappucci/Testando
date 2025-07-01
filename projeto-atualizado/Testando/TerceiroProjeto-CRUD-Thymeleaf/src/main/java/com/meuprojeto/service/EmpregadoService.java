package com.meuprojeto.service;

import com.meuprojeto.model.Empregado;
import java.util.Optional;

public interface EmpregadoService {
    Optional<Empregado> findByEmail(String email);
    Empregado salvarEmpregado(Empregado empregado);
    void trocarSenha(String email, String novaSenha);
    boolean validarCredenciais(String email, String senha);
}

