package com.tutoriasitste.tutorias.residencia.app.models.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //id
	
	@NotBlank(message = "No puede contener espacios en blanco")
	@Column(unique = true,length = 20)
	private String username;
	
	@NotEmpty
	@Column(length = 60)
	private String password;
	
	private Boolean enabled;
	
	@JsonIgnoreProperties(value={"usuario","hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario",cascade = CascadeType.ALL)
	private List<Archivo> archivos;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name="usuarios_roles", joinColumns= @JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="roles_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "roles_id"})})
	private List<Role> roles;

	public Usuario() {		
		this.archivos = new ArrayList<>();
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
    public void addRol(Role role) {
    	if (roles == null) {
			roles = new LinkedList<>();
		}
     	roles.add(role);
    }


	public List<Archivo> getArchivos() {
		return archivos;
	}


	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

    
}

