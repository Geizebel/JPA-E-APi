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

import br.com.apisEjpa.models.Peca;
import br.com.apisEjpa.repository.PecaRepository;

@RestController
@RequestMapping("api/pecas")

public class APIPeca {

	@Autowired
	private PecaRepository pr;
	
	@GetMapping("")
	public ResponseEntity<List<Peca>> getAllPeca(){
		
		List<Peca> pecas = pr.findAll();
		
		if (pecas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Peca>>(pecas, HttpStatus.OK);			
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Peca> getOneCliente(@PathVariable(value= "id") long id){
		Optional<Peca> peca = pr.findById(id);
		
		if(!peca.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<Peca>(peca.get(),HttpStatus.OK);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Peca> save(@RequestBody @Validated Peca peca){
		 return new ResponseEntity<Peca>(pr.save(peca), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Peca> update(@PathVariable(value="id") long id, @RequestBody @Validated Peca peca){
		
		Optional<Peca> pecaRet= pr.findById(id);
		
		if(!pecaRet.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} else {
			peca.setCodigo(pecaRet.get().getCodigo());
			return new ResponseEntity<Peca>(pr.save(peca), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")long id){
		
		Optional<Peca> peca = pr.findById(id);
		
		if(!peca.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			pr.delete(peca.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
}
