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

import com.loja.faculdade.entidades.Usuario;
import com.loja.faculdade.entidades.exception.InvalidUserException;
import com.loja.faculdade.entidades.exception.UserNotFoundException;
import com.loja.faculdade.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAllUsers() {
		List<Usuario> list = usuarioService.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
		try {
			Usuario usuario = usuarioService.findById(id);
			return ResponseEntity.ok(usuario);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> insertUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario usuario1 = usuarioService.insert(usuario);
			return ResponseEntity.ok(usuario1);
		} catch (InvalidUserException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario updatedUsuario) {
		try {
			updatedUsuario.setId(id);
			Usuario usuario1 = usuarioService.update(updatedUsuario);

			if (usuario1 != null) {
				return ResponseEntity.ok(usuario1);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
		try {
			usuarioService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
