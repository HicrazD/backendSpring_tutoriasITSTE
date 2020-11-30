package com.tutoriasitste.tutorias.residencia.app.services.docentes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Docente;
import com.tutoriasitste.tutorias.residencia.app.models.repository.DocenteRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;

@Service
public class DocenteServiceImpl extends CommonServiceImpl<Docente, DocenteRepository> implements DocenteService {

	@Override
	@Transactional(readOnly = true)
	public Docente findDocenteByStudent(Long id) {
		// TODO Auto-generated method stub
		return repository.findDocenteByStudent(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Docente findDocenteByUsuarioUsername(String term) {
		// TODO Auto-generated method stub
		return repository.findDocenteByUsuarioUsername(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Docente> findByNombreOrApellido(String term) {
		// TODO Auto-generated method stub
		return repository.findByNombreOrApellido(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Docente> findByDivisionIsc(String division,String term) {
		// TODO Auto-generated method stub
		return repository.findByDivisionIsc(division,term);
	}

}
