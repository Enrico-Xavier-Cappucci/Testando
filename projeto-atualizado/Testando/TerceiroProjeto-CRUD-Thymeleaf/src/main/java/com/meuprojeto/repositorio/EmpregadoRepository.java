package com.meuprojeto.repositorio;

import com.meuprojeto.model.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
    Optional<Empregado> findByEmail(String email);
    boolean existsByEmail(String email);
}

