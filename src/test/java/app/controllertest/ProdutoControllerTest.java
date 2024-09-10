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

import app.controller.ProdutoController;
import app.entity.Produto;
import app.repository.ProdutoRepository;

@SpringBootTest
public class ProdutoControllerTest {

	@Autowired
	ProdutoController produtoController;

	@MockBean
	ProdutoRepository produtoRepository;

	@BeforeEach
	void setup() {

		Produto produto = new Produto();
		produto.setId(1L);
		produto.setDescricao("descrever produto");
		produto.setNome("Julia Moraes de Oliveira");
		produto.setPreco(5);
		produto.setVendas(new ArrayList<>());

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

		// Mockito.when(metodo).thenReturn(objeto / lista / etc);
		Mockito.when(this.produtoRepository.findAll()).thenReturn(produtos);
		Mockito.when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
	}

	@Test
	@DisplayName("Teste de integração - conseguiu retornar a lista")
	void cenarioFindAll() {
		ResponseEntity<List<Produto>> retorno = this.produtoController.findAll();
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração - tamanho da lista")
	void cenarioFindAllTamanho() {
		ResponseEntity<List<Produto>> retorno = this.produtoController.findAll();
		List<Produto> lista = retorno.getBody();
		Assertions.assertEquals(2, lista.size());
	}

	@Test
	@DisplayName("Teste de integração - findById")
	void cenarioFindById() {
		ResponseEntity<Produto> retorno = this.produtoController.findById(1L);
		Produto response_produto = retorno.getBody();
		Assertions.assertEquals(1L, response_produto.getId()); 
		Assertions.assertEquals("Julia Moraes de Oliveira", response_produto.getNome()); 

	}

	@Test
	@DisplayName("Teste de integração - findById ERRO")
	void cenarioFindByIdErro() {
		ResponseEntity<Produto> retorno = this.produtoController.findById(-1L);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração - save")
	void cenarioSave() {
		
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setDescricao("descrever produto");
		produto.setNome("Julia Moraes de Oliveira");
		produto.setPreco(5);
		produto.setVendas(new ArrayList<>());
		
		Mockito.when(this.produtoRepository.save(Mockito.any())).thenReturn(produto);

		ResponseEntity<String> retorno = this.produtoController.save(produto);
		
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Produto cadastrado com sucesso.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - save ERRO")
	void cenarioSaveErro() {
		ResponseEntity<String> retorno = this.produtoController.save(null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
		Assertions.assertEquals("Não há dados para salvar.", retorno.getBody());
	}
	
	@Test
	@DisplayName("Teste de integração - delete")
	void cenarioDelete() {
		ResponseEntity<String> retorno = this.produtoController.deleteById(1L);
		Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
		Assertions.assertEquals("Produto deletado com sucesso.", retorno.getBody());
	}

	@Test
	@DisplayName("Teste de integração - delete ERRO")
	void cenarioDeleteErro() {
		ResponseEntity<String> retorno = this.produtoController.deleteById(5L);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
	}

}
