package com.meuprojeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.meuprojeto.model.Empregado;
import com.meuprojeto.repositorio.RepositorioEmpregado;

@Service
public class ServicoEmpregadoImpl implements ServicoEmpregado{

	@Autowired
	private RepositorioEmpregado repositorioEmpregado;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Empregado> getAllEmpregados() {
		return repositorioEmpregado.findAll();
	}

	@Override
	public void salvarEmpregado(Empregado empregado) {
		// Se é um novo empregado, definir senha padrão
		if (empregado.getId() == null) {
			empregado.setSenha(passwordEncoder.encode("senha123"));
			empregado.setPrimeiroLogin(true);
		}
		this.repositorioEmpregado.save(empregado);
		
	}

	@Override
	public Empregado getEmpregadoById(long id) {
		Optional<Empregado> optional = repositorioEmpregado.findById(id);
		Empregado empregado = null;
		if(optional.isPresent()) {
			empregado = optional.get();
			}else {
				throw new RuntimeException("Empregado não encontrado pelo Id " + id);
			}
			return empregado;
	}

	@Override
	public void deleteEmpregadoById(long id) {
		this.repositorioEmpregado.deleteById(id);
		
	}

	@Override
	public Page<Empregado> findPaginated(int pageNo, int pageSi, String sortField, String sortDirecao) {
		
		Sort sort = sortDirecao.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		// classe que implementa a interface que das as infos para criar a paginacao
		// page-1 pelo oq eu entendi cria os numeros la embaixo na pagina começando pelo numero 1
		Pageable pageable = PageRequest.of(pageNo - 1 , pageSi, sort);
		return this.repositorioEmpregado.findAll(pageable);
	}

}
