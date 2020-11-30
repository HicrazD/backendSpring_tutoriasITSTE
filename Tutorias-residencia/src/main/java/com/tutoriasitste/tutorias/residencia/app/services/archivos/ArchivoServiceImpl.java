package com.tutoriasitste.tutorias.residencia.app.services.archivos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Archivo;
import com.tutoriasitste.tutorias.residencia.app.models.repository.ArchivoRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;
@Service
public class ArchivoServiceImpl extends CommonServiceImpl<Archivo, ArchivoRepository> implements ArchivoServices {

	@Override
	@Transactional(readOnly = true)
	public List<Archivo> findArchivoByUsuarioId(Long id) {
		// TODO Auto-generated method stub
		return repository.findArchivoByUsuarioId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Archivo> findByTipo(String term) {
		// TODO Auto-generated method stub
		return repository.findByTipo(term);
	}

}
