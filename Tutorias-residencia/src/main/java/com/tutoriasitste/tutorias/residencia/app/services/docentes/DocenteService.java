package com.tutoriasitste.tutorias.residencia.app.services.docentes;

import java.util.List;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Docente;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonService;

public interface DocenteService extends CommonService<Docente> {

	public Docente findDocenteByStudent(Long id);
	public Docente findDocenteByUsuarioUsername(String term);
	public List<Docente> findByNombreOrApellido(String term);
	public List<Docente> findByDivisionIsc(String division,String term);
}
