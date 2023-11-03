package com.loja.faculdade.entidades;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loja.faculdade.entidades.enums.UserRoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @JsonProperty("roles")
    private UserRoleEnum roles ;

    public Usuario() {

    }

    public Usuario(String username, String senha, UserRoleEnum role) {
        this.username = username;
        this.password = senha;
        this.roles = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public UserRoleEnum getRoles() {
		return roles;
	}

	public void setRoles(UserRoleEnum roles) {
		this.roles = roles;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id);
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return id == other.id;
    }


}