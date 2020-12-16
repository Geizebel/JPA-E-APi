package br.com.apisEjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apisEjpa.models.Moto;

public interface MotoRepository  extends JpaRepository<Moto, Long> {
	Moto findByCodigo(long id);
}
