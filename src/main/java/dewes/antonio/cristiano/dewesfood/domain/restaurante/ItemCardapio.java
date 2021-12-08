package dewes.antonio.cristiano.dewesfood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import dewes.antonio.cristiano.dewesfood.infrastructure.web.validator.UploadConstraint;
import dewes.antonio.cristiano.dewesfood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "item_cardapio")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCardapio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max=50)
	private String nome;
	
	@NotBlank(message = "A categoria não pode ser vazia")
	@Size(max=25)
	private String categoria;
	
	@NotBlank(message = "A descrição não pode ser vazia")
	@Size(max=80)
	private String descricao;
	
	@Size(max=50)
	private String image;
	
	@UploadConstraint(acceptedTypes = {FileType.PNG, FileType.JPG}, message = "O arquivo não é um arquivo válido")
	private transient MultipartFile imageFile;
	
	@NotNull(message = "O preço não pode ser vazio")
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private boolean destaque;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	public void setImageFileName() {
		if(getId() == null) {
			throw new IllegalStateException("O objeto precisa primeiro ser criado");
		}
		
		this.image = String.format("%04d-comida.%s", getId(), FileType.of(imageFile.getContentType()).getExtension());
	}
}
