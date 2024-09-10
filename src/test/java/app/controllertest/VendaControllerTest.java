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

import app.controller.VendaController;
import app.entity.Cliente;
import app.entity.Funcionario;
import app.entity.Produto;
import app.entity.Venda;
import app.repository.VendaRepository;

@SpringBootTest
public class VendaControllerTest {

	@Autowired
	VendaController vendaController;

	@MockBean
	VendaRepository vendaRepository;

	@BeforeEach
	void setup() {

		Venda venda = new Venda();
		venda.setId(1L);
		venda.setValorTotal(30);
		venda.setProdutos(new ArrayList<>());
		venda.setObservacao("Algo a observar");
		venda.setCliente(new Cliente());
		venda.setFuncionario(new Funcionario());

		List<Venda> vendas = new ArrayList<>();
		vendas.add(new Venda(
				1L,
				new Cliente(),
				new Funcionario(),
				new ArrayList<>(),
				"algo a observar",
				45.0));;
		vendas.add(new Venda(
				2L,
				new Cliente(),
				new Funcionario(),
				new ArrayList<>(),
				"algo a observar",
				45.0));;

		// Mockito.when(metodo).thenReturn(objeto / lista / etc);
		Mockito.when(this.vendaRepository.findAll()).thenReturn(vendas);
		Mockito.when(this.vendaRepository.findById(1L)).thenReturn(Optional.of(venda));
	}

	@Test
	@DisplayName("Teste de integração - conseguiu retornar a lista")
	void cenarioFindAll() {
		ResponseEntity<List<Venda>> retorno = this.vendaController.findAll();
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração - tamanho da lista")
	void cenarioFindAllTamanho() {
		ResponseEntity<List<Venda>> retorno = this.vendaController.findAll();
		List<Venda> lista = retorno.getBody();
		Assertions.assertEquals(2, lista.size());
	}

	@Test
	@DisplayName("Teste de integração - findById")
	void cenarioFindById() {
		ResponseEntity<Venda> retorno = this.vendaController.findById(1L);
		Venda response_venda = retorno.getBody();
		Assertions.assertEquals(1L, response_venda.getId()); 
	}

	@Test
	@DisplayName("Teste de integração - findById ERRO")
	void cenarioFindByIdErro() {
		ResponseEntity<Venda> retorno = this.vendaController.findById(-1L);
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
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto(
				1L,
				"Produto 1",
				"descrever produto",
				5,
				new ArrayList<>()));
		produtos.add(new Produto(
				2L,
				"Produto 2",
				"descrever produto",
				10,
				new ArrayList<>()));
		
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

		Venda venda = new Venda();
		venda.setId(1L);
		venda.setValorTotal(30);
		venda.setProdutos(produtos);
		venda.setObservacao("Algo a observar");
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		
		Mockito.when(this.vendaRepository.save(Mockito.any())).thenReturn(venda);

		ResponseEntity<String> retorno = this.vendaController.save(venda);
		
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Venda cadastrada com sucesso.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - save ERRO")
	void cenarioSaveErro() {
		ResponseEntity<String> retorno = this.vendaController.save(null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
		Assertions.assertEquals("Não há dados para salvar.", retorno.getBody());
	}
	
	@Test
	@DisplayName("Teste de integração - delete")
	void cenarioDelete() {
		ResponseEntity<String> retorno = this.vendaController.deleteById(1L);
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Venda deletada com sucesso!", retorno.getBody());
	}
	
	@Test
	@DisplayName("Teste de integração - delete ERRO")
	void cenarioDeleteErro() {
		ResponseEntity<String> retorno = this.vendaController.deleteById(5L);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}
	
	

}
