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

import br.com.apisEjpa.models.Vendedor;
import br.com.apisEjpa.repository.VendedorRepository;

@RestController
@RequestMapping("api/vendedores")

public class APIVendedor {

	@Autowired
	private VendedorRepository vr;
	
	@GetMapping("")
	public ResponseEntity<List<Vendedor>> getAllVendedor(){
		
		List<Vendedor> vendedores = vr.findAll();
		
		if (vendedores.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Vendedor>>(vendedores, HttpStatus.OK);			
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Vendedor> getOneCliente(@PathVariable(value= "id") long id){
		Optional<Vendedor> vendedor = vr.findById(id);
		
		if(!vendedor.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<Vendedor>(vendedor.get(),HttpStatus.OK);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Vendedor> save(@RequestBody @Validated Vendedor vendedor){
		 return new ResponseEntity<Vendedor>(vr.save(vendedor), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vendedor> update(@PathVariable(value="id") long id, @RequestBody @Validated Vendedor vendedor){
		
		Optional<Vendedor> vendedorRet= vr.findById(id);
		
		if(!vendedorRet.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} else {
			vendedor.setCodigo(vendedorRet.get().getCodigo());
			return new ResponseEntity<Vendedor>(vr.save(vendedor), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")long id){
		
		Optional<Vendedor> vendedor = vr.findById(id);
		
		if(!vendedor.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			vr.delete(vendedor.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
}
