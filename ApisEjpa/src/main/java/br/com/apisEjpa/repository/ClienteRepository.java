package br.com.apisEjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apisEjpa.models.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

	Cliente findByCodigo(long id);
}
