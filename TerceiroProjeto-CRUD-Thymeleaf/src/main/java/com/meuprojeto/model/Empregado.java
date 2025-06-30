package com.meuprojeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empregados")
public class Empregado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Primeiro nome")
	private String primeiroNome;
	
	@Column(name = "Sobrenome")
	private String sobrenome;
	
	@Column(name = "Email")
	private String email;
	
	@Column(nullable = false)
    private String senha;
    
    @Column(name = "senha_trocada", nullable = false)
    private boolean senhaTrocada;

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
	    
	public boolean isSenhaTrocada() {
		return senhaTrocada;
	}
	    
	public void setSenhaTrocada(boolean senhaTrocada) {
		this.senhaTrocada = senhaTrocada;
	
	}
	
}
