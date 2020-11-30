package com.tutoriasitste.tutorias.residencia.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.tutoriasitste.tutorias.residencia.app.models.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long>{

	public Docente findByNombre(String nombre);
	
	@Query("select d from Docente d join fetch d.alumnos a where a.id=?1")
	public Docente findDocenteByStudent(Long id);
	
	@Query("select d from Docente d join fetch d.usuario u where u.username=?1")
	public Docente findDocenteByUsuarioUsername(String term);
	
	@Query("select d from Docente d where d.nombre like %?1% or d.apellido like %?1%")
	public List<Docente> findByNombreOrApellido(String term);
	
	@Query("select d from Docente d where d.division=?1 and (d.nombre like %?2% or d.apellido like %?2%)")
	public List<Docente> findByDivisionIsc(String division,String term);
	

	
}
