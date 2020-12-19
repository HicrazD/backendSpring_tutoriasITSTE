package com.tutoriasitste.tutorias.residencia.app.services.alumnos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Respuesta;
import com.tutoriasitste.tutorias.residencia.app.models.repository.RespuestaRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	private RespuestaRepository repository;
	
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}
}
