package dewes.antonio.cristiano.dewesfood.domain.restaurante;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // define que é uma entidade
@Table(name = "categoria_restaurante") // especifica um nome para a tabela, usar quando for diferente do nome da classe
public class CategoriaRestaurante implements Serializable {

	@Id // especifica que esse é o id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@NotNull
	@Size(max = 20)
	private String nome;

	@NotNull
	@Size(max = 50)
	private String imagem;
	
	@ManyToMany(mappedBy = "categorias")//esse direcionamento permite que eu encontre os restaurantes por meio da categoria
	private Set<Restaurante> restaurantes = new HashSet<>(0);
}