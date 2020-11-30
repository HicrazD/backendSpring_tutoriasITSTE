package com.tutoriasitste.tutorias.residencia.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.tutoriasitste.tutorias.residencia.app.models.entity.Archivo;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

	@Query("select a from Archivo a join fetch a.usuario u where u.id=?1")
	public List<Archivo> findArchivoByUsuarioId(Long id);
	
	@Query("select a from Archivo a where a.tipo like %?1%")
	public List<Archivo> findByTipo(String term);
}
