package com.meuprojeto.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.meuprojeto.model.Empregado;

@Repository
public interface RepositorioEmpregado extends JpaRepository<Empregado, Long> {
	

}
