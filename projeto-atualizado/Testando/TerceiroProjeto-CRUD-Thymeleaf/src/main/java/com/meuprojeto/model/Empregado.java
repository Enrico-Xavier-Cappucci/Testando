package com.meuprojeto.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "empregados")
public class Empregado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "primeiro_nome")
	private String primeiroNome;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "primeiro_login")
	private boolean primeiroLogin = true;
	
	@OneToMany(mappedBy = "empregado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tarefa> tarefas;

	// Construtores
	public Empregado() {}
	
	public Empregado(String primeiroNome, String sobrenome, String email) {
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = "senha123"; // Senha padrão
		this.primeiroLogin = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isPrimeiroLogin() {
		return primeiroLogin;
	}
	
	public void setPrimeiroLogin(boolean primeiroLogin) {
		this.primeiroLogin = primeiroLogin;
	}
	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
}
