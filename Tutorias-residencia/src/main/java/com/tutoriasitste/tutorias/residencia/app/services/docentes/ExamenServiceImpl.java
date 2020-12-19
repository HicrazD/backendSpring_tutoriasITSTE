/**
 * 
 */
package com.tutoriasitste.tutorias.residencia.app.services.docentes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Examen;
import com.tutoriasitste.tutorias.residencia.app.models.repository.ExamenRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;

/**
 * @author Doci
 *
 */
@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}
}
