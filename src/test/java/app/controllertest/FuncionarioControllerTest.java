package app.controllertest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.controller.FuncionarioController;
import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@SpringBootTest
public class FuncionarioControllerTest {

	@Autowired
	FuncionarioController funcionarioController;

	@MockBean
	FuncionarioRepository funcionarioRepository;

	@BeforeEach
	void setup() {

		Funcionario funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setCpf("083.727.439-75");
		funcionario.setEmail("julia.oliv4@outlook.com");
		funcionario.setIdade(19);
		funcionario.setNome("Julia Moraes de Oliveira");
		funcionario.setTelefone("45 998621617");
		funcionario.setEndereco("Rua anthonia gilly, 182");
		funcionario.setFuncao("vendedor");
		funcionario.setVendas(new ArrayList<>());

		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionario(
				1L,
				"Maria de Brito",
				18,
				"083.727.439-75",
				"45 998621617",
				"maria.oliv4@outlook.com",
				"Rua anthonia gilly, 182",
				"vendedor",
				new ArrayList<>()));
		funcionarios.add(new Funcionario(
				1L,
				"Paulo de Brito",
				18,
				"083.727.439-75",
				"45 998621617",
				"paulo.oliv4@outlook.com",
				"Rua anthonia gilly, 182",
				"vendedor",
				new ArrayList<>()));

		// Mockito.when(metodo).thenReturn(objeto / lista / etc);
		Mockito.when(this.funcionarioRepository.findAll()).thenReturn(funcionarios);
		Mockito.when(this.funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
	}

	@Test
	@DisplayName("Teste de integração - conseguiu retornar a lista")
	void cenarioFindAll() {
		ResponseEntity<List<Funcionario>> retorno = this.funcionarioController.findAll();
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração - tamanho da lista")
	void cenarioFindAllTamanho() {
		ResponseEntity<List<Funcionario>> retorno = this.funcionarioController.findAll();
		List<Funcionario> lista = retorno.getBody();
		Assertions.assertEquals(2, lista.size());
	}

	@Test
	@DisplayName("Teste de integração - findById")
	void cenarioFindById() {
		ResponseEntity<Funcionario> retorno = this.funcionarioController.findById(1L);
		Funcionario response_funcionario = retorno.getBody();
		Assertions.assertEquals(1L, response_funcionario.getId()); 
		Assertions.assertEquals("Julia Moraes de Oliveira", response_funcionario.getNome()); 

	}

	@Test
	@DisplayName("Teste de integração - findById ERRO")
	void cenarioFindByIdErro() {
		ResponseEntity<Funcionario> retorno = this.funcionarioController.findById(-1L);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração - save")
	void cenarioSave() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setCpf("083.727.439-75");
		funcionario.setEmail("julia.oliv4@outlook.com");
		funcionario.setIdade(19);
		funcionario.setNome("Julia Moraes de Oliveira");
		funcionario.setTelefone("45 998621617");
		funcionario.setEndereco("Rua anthonia gilly, 182");
		funcionario.setFuncao("vendedor");
		funcionario.setVendas(new ArrayList<>());

		Mockito.when(this.funcionarioRepository.save(Mockito.any())).thenReturn(funcionario);

		ResponseEntity<String> retorno = this.funcionarioController.save(funcionario);
		
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Funcionario salvo com sucesso.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - save ERRO")
	void cenarioSaveErro() {
		ResponseEntity<String> retorno = this.funcionarioController.save(null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
		Assertions.assertEquals("Não há dados para salvar.", retorno.getBody());
	}
	
	@Test
	@DisplayName("Teste de integração - delete")
	void cenarioDelete() {
		ResponseEntity<String> retorno = this.funcionarioController.delete(1L);
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Funcionario deletado com sucesso.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - delete ERRO")
	void cenarioDeleteErro() {
		ResponseEntity<String> retorno = this.funcionarioController.delete(5L);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}
}
