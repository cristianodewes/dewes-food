package dewes.antonio.cristiano.dewesfood.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dewes.antonio.cristiano.dewesfood.domain.cliente.Cliente;
import dewes.antonio.cristiano.dewesfood.domain.cliente.ClienteRepository;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.CategoriaRestaurante;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.CategoriaRestauranteRepository;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.ItemCardapio;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.ItemCardapioRepository;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.Restaurante;
import dewes.antonio.cristiano.dewesfood.domain.restaurante.RestauranteRepository;
import dewes.antonio.cristiano.dewesfood.util.StringUtils;

@Component
public class InsertDataForTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {		
		clientes();
		Restaurante[] restaurantes = restaurantes();
		itensCardapio(restaurantes);
	}
	
	private Restaurante[] restaurantes() {
		List<Restaurante> restaurantes = new ArrayList<>();
		
		CategoriaRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow();
		CategoriaRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow();
		CategoriaRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(5).orElseThrow();
		CategoriaRestaurante categoriaJapones = categoriaRestauranteRepository.findById(6).orElseThrow();
		
		Restaurante r = new Restaurante();
		r.setNome("Bubger King");
		r.setEmail("r1@dewesfood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000101");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("51996033927");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0001-logo.png");
		r.setTempoEntregaBase(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Mc Naldo's");
		r.setEmail("r2@dewesfood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000102");
		r.setTaxaEntrega(BigDecimal.valueOf(4.5));
		r.setTelefone("51996033928");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0002-logo.png");
		r.setTempoEntregaBase(25);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Sbubby");
		r.setEmail("r3@dewesfood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000103");
		r.setTaxaEntrega(BigDecimal.valueOf(12.2));
		r.setTelefone("51996033929");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0003-logo.png");
		r.setTempoEntregaBase(38);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Pizza Brut");
		r.setEmail("r4@dewesfood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000104");
		r.setTaxaEntrega(BigDecimal.valueOf(9.8));
		r.setTelefone("51996033930");
		r.getCategorias().add(categoriaPizza);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0004-logo.png");
		r.setTempoEntregaBase(22);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Wiki Japa");
		r.setEmail("r5@dewesfood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("00000000000105");
		r.setTaxaEntrega(BigDecimal.valueOf(14.9));
		r.setTelefone("51996033931");
		r.getCategorias().add(categoriaJapones);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0005-logo.png");
		r.setTempoEntregaBase(19);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		Restaurante[] array = new Restaurante[restaurantes.size()];		
		return restaurantes.toArray(array);
	}
	
	private Cliente[] clientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente c = new Cliente();
		c.setNome("Cris");
		c.setCpf("00000000001");
		c.setEmail("cris@dewesfood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("95773000");
		c.setTelefone("51996033927");
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setNome("Ramoni");
		c.setCpf("00000000002");
		c.setEmail("ramoni@dewesfood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("95770000");
		c.setTelefone("51996033928");
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setNome("Megi Dewes");
		c.setCpf("00000000003");
		c.setEmail("ramegi@dewesfood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("95773000");
		c.setTelefone("51996033929");
		clienteRepository.save(c);
		
		Cliente[] array = new Cliente[clientes.size()];
		return clientes.toArray(array);
	}
	
	private void itensCardapio(Restaurante[] restaurantes) {
		ItemCardapio ic = new ItemCardapio();
		ic.setCategoria("Sanduiche");
		ic.setDescricao("Delicioso sanduiche");
		ic.setNome("Double Cheese Burger Special");
		ic.setPreco(BigDecimal.valueOf(23.8));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(true);
		ic.setImage("0001-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Sanduiche");
		ic.setDescricao("Sanduiche padrão");
		ic.setNome("Cheese Burger Simples");
		ic.setPreco(BigDecimal.valueOf(17.8));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImage("0006-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Sanduiche");
		ic.setDescricao("Sanduiche natural");
		ic.setNome("Sanduiche natural");
		ic.setPreco(BigDecimal.valueOf(11.8));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImage("0007-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Bebida");
		ic.setDescricao("Refrigerante");
		ic.setNome("Refrigerante Tradicional Coca");
		ic.setPreco(BigDecimal.valueOf(8.5));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImage("0004-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Bebida");
		ic.setDescricao("Suco natural");
		ic.setNome("Suco de uva");
		ic.setPreco(BigDecimal.valueOf(10));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(false);
		ic.setImage("0005-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Pizza");
		ic.setDescricao("Pizza calabresa saborosa");
		ic.setNome("Pizza de calabresa");
		ic.setPreco(BigDecimal.valueOf(61.3));
		ic.setRestaurante(restaurantes[3]);
		ic.setDestaque(false);
		ic.setImage("0002-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Japonesa");
		ic.setDescricao("Uramaki tradicional");
		ic.setNome("Uramaki");
		ic.setPreco(BigDecimal.valueOf(16.9));
		ic.setRestaurante(restaurantes[4]);
		ic.setDestaque(false);
		ic.setImage("0003-comida.png");
		itemCardapioRepository.save(ic);
	}
}
