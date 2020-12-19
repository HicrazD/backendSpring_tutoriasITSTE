package com.tutoriasitste.tutorias.residencia.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
	
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);
}
