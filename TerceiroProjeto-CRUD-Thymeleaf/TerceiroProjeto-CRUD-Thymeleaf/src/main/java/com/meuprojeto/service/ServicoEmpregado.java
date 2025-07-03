package com.meuprojeto.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.meuprojeto.model.Empregado;

public interface ServicoEmpregado {
	List<Empregado> getAllEmpregados();
	void salvarEmpregado(Empregado empregado);
	Empregado getEmpregadoById(long id);
	void deleteEmpregadoById(long id);
	
	// para adicionar a paginacao tem que criar um novo metodo / agora vou adicionar para poder filtrar por nome e sobrenome
	Page<Empregado> findPaginated(int pageNo, int pageSi, String sortField, String sortDirecao);

}
