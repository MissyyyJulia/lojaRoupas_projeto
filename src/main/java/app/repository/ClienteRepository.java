package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("FROM Cliente c WHERE c.idade BETWEEN 18 AND 35")
	public List<Cliente> findByIdadeEntre18e35();

}
