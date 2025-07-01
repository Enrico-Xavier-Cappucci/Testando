package com.meuprojeto.repositorio;

import com.meuprojeto.model.Tarefa;
import com.meuprojeto.model.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByEmpregado(Empregado empregado);
    List<Tarefa> findByEmpregadoOrderByDataLimiteAsc(Empregado empregado);
    List<Tarefa> findAllByOrderByDataLimiteAsc();
}

