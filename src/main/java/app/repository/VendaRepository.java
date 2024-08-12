package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	//pesquisar parte do nome do cliente na venda
	public List<Venda> findByClienteNomeContaining(String nome);
    public List<Venda> findByFuncionarioNomeContaining(String nome);
    public List<Venda> findTop10ByOrderByValorTotalDesc();
    
}
