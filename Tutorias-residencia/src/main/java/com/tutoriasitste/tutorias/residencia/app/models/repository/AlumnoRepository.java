package com.tutoriasitste.tutorias.residencia.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> findByNombreOrApellido(String term);

	
	@Query("select a from Alumno a join fetch a.usuario u where u.username=?1")
	public Alumno findAlumnoByUsuarioUsername(String term);
	
	@Query("from Alumno a where a.matricula like ?1")
	public Alumno findByMatricula(Long matricula);
}
