package app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Pattern(regexp = "\\b\\w+\\b\\s+\\b\\w+\\b")
    private String nome;
    
    @Min(1)
    private int idade;
    
    @CPF
    private String cpf;
    
    //@Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")
    private String telefone;
    
    @Email
    private String email;
    
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;
    
    @NotBlank
    private String endereco;
    
   @OneToMany(mappedBy = "cliente")
   @JsonIgnoreProperties("vendas")
   private List<Venda> vendas;

    
}
