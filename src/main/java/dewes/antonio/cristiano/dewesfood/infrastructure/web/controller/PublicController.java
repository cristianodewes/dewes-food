package dewes.antonio.cristiano.dewesfood.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dewes.antonio.cristiano.dewesfood.application.service.ClienteService;
import dewes.antonio.cristiano.dewesfood.application.service.RestauranteService;
import dewes.antonio.cristiano.dewesfood.application.service.ValidationException;
import dewes.antonio.cristiano.dewesfood.domain.cliente.Cliente;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.CategoriaRestauranteRepository;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.Restaurante;

@Controller
@RequestMapping(path = "/public")
public class PublicController {
	
	@Autowired
	public ClienteService clienteService; 
	
	@Autowired
	public RestauranteService restauranteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@GetMapping("/cliente/new")
	public String newCliente(Model model) {		
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
	
	@GetMapping("/restaurante/new")
	public String newRestaurante(Model model) {		
		model.addAttribute("restaurante", new Restaurante());
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		
		return "restaurante-cadastro";
	}
	
	@PostMapping("/cliente/save")
	public String saveCliente(
			@ModelAttribute("cliente") @Valid Cliente cliente,
			Errors errors,
			Model model) {
		
		if(!errors.hasErrors()) {			
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "O cliente foi cadastrado com sucesso");
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());				
			}
			
		}		
		
		
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
	
	@PostMapping("/restaurante/save")
	public String saveRestaurante(
			@ModelAttribute("restaurante") @Valid Restaurante restaurante,
			Errors errors,
			Model model) {
		
		if(!errors.hasErrors()) {			
			try {
				restauranteService.saveRestaurante(restaurante);
				model.addAttribute("msg", "O restaurante foi cadastrado com sucesso");
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());				
			}

		}		
		
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
}
