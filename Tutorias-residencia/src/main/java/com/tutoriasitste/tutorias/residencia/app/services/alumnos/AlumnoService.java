package com.tutoriasitste.tutorias.residencia.app.services.alumnos;

import java.util.List;
import com.tutoriasitste.tutorias.residencia.app.models.entity.Alumno;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno> {
	

	public List<Alumno> findByNombreOrApellido(String term);
	public Alumno findAlumnoByUsuarioUsername(String term);
	public Alumno findByMatricula(Long matricula);
}
