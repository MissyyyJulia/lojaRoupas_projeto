package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public String save(Cliente cliente) {
		
		if(cliente != null) {
			this.clienteRepository.save(cliente);
			return "Cliente cadastrado com sucesso.";
		} else {
			return "Não há dados para salvar.";
		}
	
	
	}

	public String update(Cliente cliente, long id) {
		cliente.setId(id);
		this.clienteRepository.save(cliente);
		return "Cliente atualizado com sucesso.";

	}

	public Cliente findById(long id) {
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	public String delete(long id) {
		this.clienteRepository.deleteById(id);
		return "Cliente deletado com sucesso.";

	}

	public List<Cliente> findByIdadeEntre18e35() {
		return this.clienteRepository.findByIdadeEntre18e35();
	}

}
