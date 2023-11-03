package com.loja.faculdade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.loja.faculdade.entidades.Usuario;
import com.loja.faculdade.entidades.exception.InvalidUserException;
import com.loja.faculdade.entidades.exception.UserNotFoundException;
import com.loja.faculdade.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(Long id) throws UserNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new UserNotFoundException("Usuário não encontrado com o ID: " + id);
		}
	}

	public Usuario insert(Usuario usuario) {
		if (usuario.getName() == null || usuario.getPassword() == null || usuario.getUsername() == null
				|| usuario.getRoles() == null) {
			throw new InvalidUserException("Campos obrigatórios não preenchidos.");
		}
		return usuarioRepository.save(usuario);
	}

	public Usuario update(Usuario usuario) throws UserNotFoundException {
	    Long id = usuario.getId();
	    if (id == null) {
	        throw new UserNotFoundException("ID de usuário não fornecido para atualização.");
	    }

	    Optional<Usuario> existingUsuario = usuarioRepository.findById(id);

	    if (existingUsuario.isPresent()) {
	        // Atualize os campos aqui
	        return usuarioRepository.saveAndFlush(usuario);
	    } else {
	        throw new UserNotFoundException("Usuário não encontrado com o ID: " + id);
	    }
	}


	public void delete(Long id) throws UserNotFoundException {
	    try {
	        usuarioRepository.deleteById(id);
	    } catch (EmptyResultDataAccessException e) {
	        throw new UserNotFoundException("Usuário não encontrado com o ID: " + id);
	    }
	}

}