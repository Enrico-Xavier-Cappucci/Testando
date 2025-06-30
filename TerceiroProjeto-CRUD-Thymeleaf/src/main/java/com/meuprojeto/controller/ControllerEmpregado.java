package com.meuprojeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meuprojeto.model.Empregado;
import com.meuprojeto.service.ServicoEmpregado;

@Controller
public class ControllerEmpregado {
//display list of employees
	
	@Autowired
	private ServicoEmpregado servicoEmpregado;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
//		model.addAttribute("listaEmpregados", servicoEmpregado.getAllEmpregados());
//		return "index";
// ia ser feito assim mas agr q tem a paginacao precisa transforma a primera em default do seguinte jeito
		return findPaginated(1, "primeiroNome", "asc", model);
		
	}
	
	@GetMapping("/showNovoEmpregadoForm")
	public String showNovoEmpregadoForm(Model model) {
		Empregado empregado = new Empregado();
		model.addAttribute("empregado", empregado);
		return "novo_empregado";
	}
	
	@PostMapping("/salvarEmpregado")
	public String salvarEmpregado(@ModelAttribute("empregado") Empregado empregado) {
		
		empregado.setSenha(passwordEncoder.encode("senha123")); 
        empregado.setSenhaTrocada(false)
		//salvar o empregado no database
		servicoEmpregado.salvarEmpregado(empregado);
		return "redirect:/";
	}
		
	@GetMapping("/showFormforUpdate/{id}")
	public String showFormforUpdate(@PathVariable (value = "id") long id, Model model) {
		//pega o empregado do service
		Empregado empregado = servicoEmpregado.getEmpregadoById(id);
		
		model.addAttribute("empregado", empregado);
		return "update_empregado";
		
	}
	
	@PostMapping("/deleteEmpregado/{id}")
	public String deleteEmpregado(@PathVariable ( value = "id" ) long id, Model model) {
		this.servicoEmpregado.deleteEmpregadoById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable ( value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirecao") String sortDirecao, 
			Model model) {
		int pageSi = 5;
		
		Page<Empregado> page = servicoEmpregado.findPaginated(pageNo, pageSi, sortField, sortDirecao);
		List<Empregado> listaEmpregados = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems" , page.getTotalElements());
		
		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDirecao",sortDirecao);
		model.addAttribute("reverseSortDirecao",sortDirecao.equals("asc") ? "desc" : "asc");
		
		
		
		model.addAttribute("listaEmpregados", listaEmpregados);
		return "index";
	}
}







