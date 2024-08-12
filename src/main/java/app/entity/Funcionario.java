package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	private int idade;
	private String telefone;
	private String email;
	private String endereco;
	private String funcao;

	/*@OneToMany(mappedBy = "funcionario")
	@JsonIgnoreProperties("vendas")
	private List<Venda> vendas;*/

}
