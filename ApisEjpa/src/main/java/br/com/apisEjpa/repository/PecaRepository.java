package br.com.apisEjpa.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apisEjpa.models.Peca;

public interface PecaRepository  extends JpaRepository<Peca, Long>{
	Peca  findByCodigo(long id);
}
