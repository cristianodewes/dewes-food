package dewes.antonio.cristiano.dewesfood.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dewes.antonio.cristiano.dewesfood.domain.cliente.Cliente;
import dewes.antonio.cristiano.dewesfood.domain.cliente.ClienteRepository;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.Restaurante;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.RestauranteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Transactional //significa que esse metodo está sendo executado como uma transação dentro do BD, cuidando da integridade do banco caso ocorra um erro
	public void saveCliente(Cliente cliente) throws ValidationException{
		
		if(!validateEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidationException("O e-mail está duplicado!");
		}
		
		if( cliente.getId() != null) {
			Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElseThrow();			
			cliente.setSenha(clienteDB.getSenha());
			
		} else {
			cliente.encryptPassword();
		}
		
		clienteRepository.save(cliente);
	}
	
	private boolean validateEmail(String email, Integer id) {
		Restaurante restaurante = restauranteRepository.findByEmail(email);
		
		if(restaurante != null) {
			return false;
		}
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente != null) {
			if(id == null) {
				return false;
			} 
			
			if( !cliente.getId().equals(id)) {
				return false;
			}
		}
		
		return true;
	}
}
