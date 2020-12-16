package br.com.apisEjpa.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisEjpa.models.Cliente;
import br.com.apisEjpa.repository.ClienteRepository;

@RestController
@RequestMapping("api/clientes")

public class APICliente {

	@Autowired
	private ClienteRepository cr;
	
	@GetMapping("")
	public ResponseEntity<List<Cliente>> getAllCliente(){
		
		List<Cliente> clientes = cr.findAll();
		
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);			
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getOneCliente(@PathVariable(value= "id") long id){
		Optional<Cliente> cliente = cr.findById(id);
		
		if(!cliente.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<Cliente>(cliente.get(),HttpStatus.OK);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Cliente> save(@RequestBody @Validated Cliente cliente){
		 return new ResponseEntity<Cliente>(cr.save(cliente), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable(value="id") long id, @RequestBody @Validated Cliente cliente){
		
		Optional<Cliente> clienteRet= cr.findById(id);
		
		if(!clienteRet.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} else {
			cliente.setCodigo(clienteRet.get().getCodigo());
			return new ResponseEntity<Cliente>(cr.save(cliente), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")long id){
		
		Optional<Cliente> cliente = cr.findById(id);
		
		if(!cliente.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			cr.delete(cliente.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
}
