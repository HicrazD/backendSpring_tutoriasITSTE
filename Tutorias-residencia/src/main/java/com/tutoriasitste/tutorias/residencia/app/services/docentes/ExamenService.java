package com.tutoriasitste.tutorias.residencia.app.services.docentes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Examen;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonService;


public interface ExamenService extends CommonService<Examen>{

	public List<Examen> findByNombre(String term);
}
