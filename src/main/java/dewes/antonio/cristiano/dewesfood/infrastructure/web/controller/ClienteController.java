package dewes.antonio.cristiano.dewesfood.infrastructure.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dewes.antonio.cristiano.dewesfood.application.service.ClienteService;
import dewes.antonio.cristiano.dewesfood.application.service.ValidationException;
import dewes.antonio.cristiano.dewesfood.domain.cliente.Cliente;
import dewes.antonio.cristiano.dewesfood.domain.cliente.ClienteRepository;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.CategoriaRestaurante;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.CategoriaRestauranteRepository;
import dewes.antonio.cristiano.dewesfood.util.SecurityUtils;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriasRestauranteRepository;
		
	@GetMapping(path = "/home")
	public String home(Model model) {
		
		List<CategoriaRestaurante> categorias =  categoriasRestauranteRepository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
		
		return "cliente-home";
	}
	
	@GetMapping(path = "/edit")
	public String edit(Model model) {
		Integer clienteId = SecurityUtils.loggedCliente().getId();
		
		Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
		model.addAttribute("cliente", cliente);
		ControllerHelper.setEditMode(model, true);
		
		return "cliente-cadastro";
	}
	
	@PostMapping(path = "/save")
	public String save(@ModelAttribute("cliente") @Valid Cliente cliente,
			Errors errors,
			Model model) {
		if(!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente Gravado com sucesso!");
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}
		
		ControllerHelper.setEditMode(model, true);
		return "cliente-cadastro";
	}
}