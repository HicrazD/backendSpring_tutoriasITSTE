package com.tutoriasitste.tutorias.residencia.app.services.alumnos;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Respuesta;

public interface RespuestaService {
	
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
