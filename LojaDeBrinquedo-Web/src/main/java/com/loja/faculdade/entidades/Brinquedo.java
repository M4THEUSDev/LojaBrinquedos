package com.loja.faculdade.entidades;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loja.faculdade.entidades.enums.CategoriaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "brinquedos")

public class Brinquedo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@JsonProperty("Category")
	private CategoriaEnum category;

	private String brand;
	@Column(nullable = false)

	private String url;

	@Column(nullable = false)
	private Double value;

	private String details;

	public Brinquedo() {

	}

	public Brinquedo(Long id, String name, String description, CategoriaEnum category, String brand, String url,
			Double value, String details) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.brand = brand;
		this.url = url;
		this.value = value;
		this.details = details;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoriaEnum getCategory() {
		return category;
	}

	public void setCategory(CategoriaEnum category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Brinquedo))
			return false;
		Brinquedo other = (Brinquedo) obj;
		return Objects.equals(id, other.id);
	}

}
