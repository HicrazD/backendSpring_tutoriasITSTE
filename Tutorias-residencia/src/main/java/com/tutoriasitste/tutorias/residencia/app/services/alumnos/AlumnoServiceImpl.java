package com.tutoriasitste.tutorias.residencia.app.services.alumnos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Alumno;
import com.tutoriasitste.tutorias.residencia.app.models.repository.AlumnoRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		// TODO Auto-generated method stub
		return repository.findByNombreOrApellido(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findAlumnoByUsuarioUsername(String term) {
		// TODO Auto-generated method stub
		return repository.findAlumnoByUsuarioUsername(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findByMatricula(Long matricula) {
		// TODO Auto-generated method stub
		return repository.findByMatricula(matricula);
	}

}
