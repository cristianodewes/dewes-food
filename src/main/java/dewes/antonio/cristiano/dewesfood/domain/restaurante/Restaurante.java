package dewes.antonio.cristiano.dewesfood.domain.restaurante;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import dewes.antonio.cristiano.dewesfood.domain.usuario.Usuario;
import dewes.antonio.cristiano.dewesfood.infrastructure.web.validator.UploadConstraint;
import dewes.antonio.cristiano.dewesfood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurante")
@SuppressWarnings("serial")
public class Restaurante extends Usuario {

	@NotBlank(message = "O CNPJ não pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ está com valor inválido")
	@Column(length = 14, nullable = true)
	private String cnpj;

	@Size(max = 80)
	private String logotipo;
	
	@UploadConstraint(acceptedTypes = {FileType.PNG, FileType.JPG}, message = "O arquivo não é suportado")
	private transient MultipartFile logotipoFile; //faz com que esse atriubuto não persista no banco de dados

	@NotNull(message = "A taxa de entrega não poder ser vazia")
	@Min(0)
	@Max(99)
	private BigDecimal taxaEntrega;

	@NotNull(message = "O tempo de entrega não pode ser vazio")
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;

	@Size(min = 1, message = "O restaurante precisa ter pelo menos uma categoria")
	@ToString.Exclude
	@ManyToMany
	@JoinTable(// cria uma tabela para fazer o relacionamento ManyToMany
			name = "restaurante_has_categoria", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id"))
	private Set<CategoriaRestaurante> categorias = new HashSet<>(0);
	
	@OneToMany(mappedBy = "restaurante") //o mappedBy define o atributo, que na classe cardapio, configura o relacionamento
	private Set<ItemCardapio> itensCardapio = new HashSet<>(0);
	
	public void setLogotipoFileName() {
		if(getId() == null) {
			throw new IllegalStateException("É preciso primeiro gravar o registro");
		}
		

	this.logotipo = String.format("%04d-logo.%s", getId(), FileType.of(logotipoFile.getContentType()).getExtension());
	}
}
