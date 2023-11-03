package com.loja.faculdade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.faculdade.entidades.Brinquedo;
import com.loja.faculdade.entidades.exception.BrinquedoNotFoundException;
import com.loja.faculdade.entidades.exception.InvalidBrinquedoException;
import com.loja.faculdade.services.BrinquedoService;



@RestController
@RequestMapping("/brinquedo")
public class BrinquedoController {
	
	@Autowired
	private BrinquedoService brinquedoservice;
	
	@GetMapping("/all")
	public ResponseEntity<List<Brinquedo>> getAll() {
		List<Brinquedo> list = brinquedoservice.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Brinquedo brinquedo = brinquedoservice.findById(id);
			return ResponseEntity.ok(brinquedo);
		} catch (BrinquedoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertBrinquedo(@RequestBody Brinquedo brinquedo) {
		try {
			Brinquedo brinquedo1 = brinquedoservice.insert(brinquedo);
			return ResponseEntity.ok(brinquedo1);
		} catch (InvalidBrinquedoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBrinquedo(@PathVariable Long id, @RequestBody Brinquedo updatedBrinquedo) {
	    try {
	        updatedBrinquedo.setId(id);
	        Brinquedo brinquedo1 = brinquedoservice.update(updatedBrinquedo);

	        if (brinquedo1 != null) {
	            return ResponseEntity.ok(brinquedo1);
	        } else {
	            // Você pode lançar a exceção aqui se necessário
	            throw new BrinquedoNotFoundException("Brinquedo não encontrado com o ID: " + id);
	        }
	    } catch (BrinquedoNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			brinquedoservice.delete(id);
			return ResponseEntity.noContent().build();
		} catch (BrinquedoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
