package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.entity.Venda;
import app.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	VendaRepository vendaRepository;

	@Autowired
	ProdutoService produtoService;

	public String save(Venda venda) {
		
		double valorTotal = this.calcularTotal(venda.getProdutos());
		venda.setValorTotal(valorTotal);
		this.vendaRepository.save(venda);
		return "Venda cadastrada com sucesso.";

	}

	public String update(Venda venda, long id) {
		venda.setId(id);
		double valorTotal = this.calcularTotal(venda.getProdutos());
		venda.setValorTotal(valorTotal);
		this.vendaRepository.save(venda);
		return "Venda Atualizada com sucesso.";
	}

	public Venda findById(long id) {
		Optional<Venda> optional = this.vendaRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();

		} else {
			return null;
		}
	}

	public List<Venda> findAll() {
		return this.vendaRepository.findAll();
	}

	public String delete(long id) {
		this.vendaRepository.deleteById(id);
		return "Venda deletada com sucesso!";
	}

	private double calcularTotal(List<Produto> produtos) {
		double valorTotal = 0;

		for (Produto p : produtos) {
			Produto produto = this.produtoService.findById(p.getId());
			valorTotal += produto.getPreco();
		}

		return valorTotal;
	}

	public List<Venda> findByCliente(String nome) {
		return this.vendaRepository.findByClienteNomeContaining(nome);
	}

	public List<Venda> findByFuncionario(String nome) {
		return this.vendaRepository.findByFuncionarioNomeContaining(nome);
	}

	public List<Venda> findTop10MaiorValorTotal() {
		return this.vendaRepository.findTop10ByOrderByValorTotalDesc();
	}
}
