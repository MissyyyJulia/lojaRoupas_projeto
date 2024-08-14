package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("vendas")
	private Cliente cliente;

	@ManyToOne
	@JsonIgnoreProperties("vendas")
	private Funcionario funcionario;

	@NotEmpty
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "produtos_no_pedido")
	@JsonIgnoreProperties("vendas")
	private List<Produto> produtos;
	
	@NotBlank
	private String observacao;
	
	@Column(name = "valor_total")
	private double valorTotal;
}
