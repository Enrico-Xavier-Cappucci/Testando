package com.meuprojeto.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "data_limite", nullable = false)
    private LocalDate dataLimite;
    
    @Column(name = "concluida", nullable = false)
    private boolean concluida = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empregado_id", nullable = false)
    private Empregado empregado;
    
    // Construtores
    public Tarefa() {}
    
    public Tarefa(String titulo, String descricao, LocalDate dataLimite, Empregado empregado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.empregado = empregado;
        this.concluida = false;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDate getDataLimite() {
        return dataLimite;
    }
    
    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }
    
    public boolean isConcluida() {
        return concluida;
    }
    
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    
    public Empregado getEmpregado() {
        return empregado;
    }
    
    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }
}

