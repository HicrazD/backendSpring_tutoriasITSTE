package com.tutoriasitste.tutorias.residencia.app.services.archivos;

import java.util.List;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Archivo;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonService;

public interface ArchivoServices extends CommonService<Archivo> {
	

	public List<Archivo> findArchivoByUsuarioId(Long id);
	public List<Archivo> findByTipo(String term);

}
