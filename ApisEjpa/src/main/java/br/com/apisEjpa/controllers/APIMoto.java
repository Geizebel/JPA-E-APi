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

import br.com.apisEjpa.models.Moto;
import br.com.apisEjpa.repository.MotoRepository;

@RestController
@RequestMapping("api/motos")

public class APIMoto {

	@Autowired
	private MotoRepository mr;
	
	@GetMapping("")
	public ResponseEntity<List<Moto>> getAllMoto(){
		
		List<Moto> motos = mr.findAll();
		
		if (motos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Moto>>(motos, HttpStatus.OK);			
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Moto> getOneMoto(@PathVariable(value= "id") long id){
		Optional<Moto> moto = mr.findById(id);
		
		if(!moto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<Moto>(moto.get(),HttpStatus.OK);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Moto> save(@RequestBody @Validated Moto moto){
		 return new ResponseEntity<Moto>(mr.save(moto), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Moto> update(@PathVariable(value="id") long id, @RequestBody @Validated Moto moto){
		
		Optional<Moto> motoRet= mr.findById(id);
		
		if(!motoRet.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} else {
			moto.setCodigo(motoRet.get().getCodigo());
			return new ResponseEntity<Moto>(mr.save(moto), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")long id){
		
		Optional<Moto> moto = mr.findById(id);
		
		if(!moto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			mr.delete(moto.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
}
