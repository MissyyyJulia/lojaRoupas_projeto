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

import app.entity.Funcionario;
import app.service.FuncionarioService;

@RestController
@RequestMapping("/app/funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionario funcionario) {
		if (funcionario == null) {
			return new ResponseEntity<>("Não há dados para salvar.", HttpStatus.BAD_REQUEST);
		}
		try {
			String mensagem = this.funcionarioService.save(funcionario);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Funcionario funcionario, @PathVariable long id) {
		try {
			String mensagem = this.funcionarioService.update(funcionario, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable long id) {
		try {
			if (id >= 0) {
				Funcionario funcionario = this.funcionarioService.findById(id);
				return new ResponseEntity<>(funcionario, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Funcionario>> findAll() {
		try {

			List<Funcionario> lista = this.funcionarioService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			Funcionario funcionario = this.funcionarioService.findById(id);
			if (funcionario == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

			}
			String mensagem = this.funcionarioService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
