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

import app.controller.ClienteController;
import app.entity.Cliente;
import app.repository.ClienteRepository;

@SpringBootTest
public class ClienteControllerTest {

	@Autowired
	ClienteController clienteController;

	@MockBean
	ClienteRepository clienteRepository;

	@BeforeEach
	void setup() {

		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setCep("85862-750");
		cliente.setCpf("083.727.439-75");
		cliente.setEmail("julia.oliv4@outlook.com");
		cliente.setIdade(19);
		cliente.setNome("Julia Moraes de Oliveira");
		cliente.setTelefone("45 998621617");
		cliente.setEndereco("Rua anthonia gilly, 182");
		cliente.setVendas(new ArrayList<>());
		
		
	 List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente(
				1L,
				"Maria de Brito",
				18,
				"083.727.439-75",
				"45 998621617",
				"julia.oliv4@outlook.com",
				"85869-750",
				"Rua anthonia gilly, 182",
				new ArrayList<>()
			)
		);
		clientes.add(new Cliente(
				2L,
				"Clarice Brasil",
				18,
				"083.727.439-75",
				"45 998621617",
				"clarice.oliv4@outlook.com",
				"85869-750",
				"Rua anthonia gilly, 182",
				new ArrayList<>()
			)
		);
		
		// Mockito.when(metodo).thenReturn(objeto / lista / etc);
		Mockito.when(this.clienteRepository.findAll()).thenReturn(clientes);
		Mockito.when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
	}


	 @Test
	 @DisplayName("Teste de integração - conseguiu retornar a lista") 
	 void cenarioFindAll() {
	 	ResponseEntity<List<Cliente>> retorno = this.clienteController.findAll();
	 	Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode()); 
	 }
	 

	@Test
	@DisplayName("Teste de integração - tamanho da lista")
	void cenarioFindAllTamanho() {
		ResponseEntity<List<Cliente>> retorno = this.clienteController.findAll();
		List<Cliente> lista = retorno.getBody();
		Assertions.assertEquals(2, lista.size());
	}
	
	@Test
	@DisplayName("Teste de integração - findById")
	void cenarioFindById() {
		ResponseEntity<Cliente> retorno = this.clienteController.findById(1L);
		Cliente response_cliente = retorno.getBody();
		Assertions.assertEquals(1L, response_cliente.getId()); //vendo se consegue pegar o id cliente
		Assertions.assertEquals("Julia Moraes de Oliveira", response_cliente.getNome()); //vendo se consegue pega o nome
		
	}
	
	@Test
	@DisplayName("Teste de integração - findById ERRO")
	void cenarioFindByIdErro() {
	    ResponseEntity<Cliente> retorno = this.clienteController.findById(-1L);
	    Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração - save")
	void cenarioSave() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setCep("85862-750");
		cliente.setCpf("083.727.439-75");
		cliente.setEmail("julia.oliv4@outlook.com");
		cliente.setIdade(19);
		cliente.setNome("Julia Moraes de Oliveira");
		cliente.setTelefone("45 998621617");
		cliente.setEndereco("Rua anthonia gilly, 182");
		cliente.setVendas(new ArrayList<>());
		
		Mockito.when(this.clienteRepository.save(Mockito.any())).thenReturn(cliente);
		
		ResponseEntity<String> retorno = this.clienteController.save(cliente);
	    Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
	    Assertions.assertEquals("Cliente cadastrado com sucesso.", retorno.getBody());
	}
	
	@Test
	@DisplayName("Teste de integração - save ERRO")
	void cenarioSaveErro() {
	    ResponseEntity<String> retorno = this.clienteController.save(null);
	    Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	    Assertions.assertEquals("Não há dados para salvar.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - delete")
	void cenarioDelete() {
		ResponseEntity<String> retorno = this.clienteController.deleteById(1L);
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Cliente deletado com sucesso.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - delete ERRO")
	void cenarioDeleteErro() {
		ResponseEntity<String> retorno = this.clienteController.deleteById(5L);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}
}