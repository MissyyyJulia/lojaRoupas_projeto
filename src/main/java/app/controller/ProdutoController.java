package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Produto;
import app.service.ProdutoService;

@RestController
@RequestMapping("/app/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Produto produto) {
		if (produto == null) {
			return new ResponseEntity<>("Não há dados para salvar.", HttpStatus.BAD_REQUEST);
		}
		try {
			String mensagem = this.produtoService.save(produto);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Produto produto, @PathVariable long id) {
		try {
			String mensagem = this.produtoService.update(produto, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		try {if (id >= 0) {
			Produto produto = this.produtoService.findById(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Produto>> findAll() {
		try {
			List<Produto> lista = this.produtoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {
		try {
			Produto produto = this.produtoService.findById(id);
			if (produto == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			}
			String mensagem = this.produtoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findTop10MaiorPreco")
	public ResponseEntity<List<Produto>> findTop10MaiorPreco() {
		try {
			List<Produto> lista = this.produtoService.findTop10MaiorPreco();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}


