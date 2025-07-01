package com.meuprojeto.service;

import com.meuprojeto.model.Tarefa;
import com.meuprojeto.model.Empregado;
import java.util.List;

public interface TarefaService {
    List<Tarefa> getAllTarefas();
    List<Tarefa> getTarefasByEmpregado(Empregado empregado);
    Tarefa salvarTarefa(Tarefa tarefa);
    Tarefa getTarefaById(Long id);
    void deleteTarefaById(Long id);
    void marcarComoConcluida(Long id);
}

