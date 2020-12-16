package br.com.apisEjpa.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apisEjpa.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

	Vendedor findByCodigo(long id);
}
