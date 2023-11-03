package com.loja.faculdade.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.faculdade.entidades.Brinquedo;
import com.loja.faculdade.entidades.exception.BrinquedoNotFoundException;
import com.loja.faculdade.repositories.BrinquedoRepository;

@Service
public class BrinquedoService {

	@Autowired
	private BrinquedoRepository brinquedoRepository;

	public List<Brinquedo> findAll() {
		return brinquedoRepository.findAll();
	}

	public Brinquedo findById(Long id) {
		Optional<Brinquedo> brinquedo = brinquedoRepository.findById(id);
		if (brinquedo.isPresent()) {
			return brinquedo.get();
		} else {
			throw new BrinquedoNotFoundException("Brinquedo não encontrado com o ID: " + id);
		}
	}

	public Brinquedo insert(Brinquedo brinquedo) {
		return brinquedoRepository.saveAndFlush(brinquedo);
	}

	public Brinquedo update(Brinquedo brinquedo) {
		Optional<Brinquedo> existingBrinquedo = brinquedoRepository.findById(brinquedo.getId());

		if (existingBrinquedo.isPresent()) {
			Brinquedo updatedBrinquedo = existingBrinquedo.get();
			updatedBrinquedo.setName(brinquedo.getName());
			updatedBrinquedo.setCategory(brinquedo.getCategory());
			updatedBrinquedo.setDetails(brinquedo.getDetails());
			updatedBrinquedo.setValue(brinquedo.getValue());
			updatedBrinquedo.setUrl(brinquedo.getUrl());
			updatedBrinquedo.setDescription(brinquedo.getDescription());
			updatedBrinquedo.setBrand(brinquedo.getBrand());
			return brinquedoRepository.save(updatedBrinquedo);
		} else {
			return null;
		}

	}

	public void delete(Long id) {
		brinquedoRepository.deleteById(id);
	}
}
