package com.meuprojeto.service;

import com.meuprojeto.model.Tarefa;
import com.meuprojeto.model.Empregado;
import com.meuprojeto.repositorio.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    @Override
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAllByOrderByDataLimiteAsc();
    }
    
    @Override
    public List<Tarefa> getTarefasByEmpregado(Empregado empregado) {
        return tarefaRepository.findByEmpregadoOrderByDataLimiteAsc(empregado);
    }
    
    @Override
    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    
    @Override
    public Tarefa getTarefaById(Long id) {
        Optional<Tarefa> optional = tarefaRepository.findById(id);
        Tarefa tarefa = null;
        if (optional.isPresent()) {
            tarefa = optional.get();
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID :: " + id);
        }
        return tarefa;
    }
    
    @Override
    public void deleteTarefaById(Long id) {
        tarefaRepository.deleteById(id);
    }
    
    @Override
    public void marcarComoConcluida(Long id) {
        Optional<Tarefa> optional = tarefaRepository.findById(id);
        if (optional.isPresent()) {
            Tarefa tarefa = optional.get();
            tarefa.setConcluida(!tarefa.isConcluida()); // Toggle do status
            tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID :: " + id);
        }
    }
}

