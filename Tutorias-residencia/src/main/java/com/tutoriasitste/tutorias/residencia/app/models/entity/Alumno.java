package com.tutoriasitste.tutorias.residencia.app.models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //id
	

	@Column(unique = true)
	@Min(7)
	@NotNull
	private Long matricula;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	private String carrera;
	
	@Email
	@Column(unique = true,length = 30)
	private String correo;
	
	@Min(0)
	@NotNull
	private Long telefono;   // Se creo las varibles telefono,Semestre, promasistencia
	
	@NotEmpty
	private String semestre;
	
	@Min(0)
	@Max(100)
	private int promAsistencia;
	
	//@NotNull
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Usuario usuario;
	


	public Alumno() {
		this.usuario = new Usuario();
	}
	
	

	public Long getTelefono() {
		return telefono;
	}



	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}



	public String getSemestre() {
		return semestre;
	}



	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}



	public int getPromAsistencia() {
		return promAsistencia;
	}



	public void setPromAsistencia(int promAsistencia) {
		this.promAsistencia = promAsistencia;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getMatricula() {
		return matricula;
	}



	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}	
	
	
	@Override
	public boolean equals(Object obj) {

		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Alumno)) {
			return false;
		}

		Alumno a = (Alumno) obj;
		
		return this.id != null && this.id.equals(a.getId());
	}

}
