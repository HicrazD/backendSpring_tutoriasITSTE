package com.tutoriasitste.tutorias.residencia.app.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Examen;
import com.tutoriasitste.tutorias.residencia.app.services.docentes.ExamenService;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;

@RestController
@RequestMapping("/api/examenes")
public class ExamenController extends CommonController<Examen, ExamenService> {

	
	//@Secured({"ROLE_ADMIN", "ROLE_DOCENTE","ROLE_ALUMNO"})
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen> o = service.findById(id);
		
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());
		
		examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb))
		.forEach(examenDb::removePregunta); 
		
		examenDb.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@Secured({ "ROLE_ADMIN","ROLE_ALUMNO","ROLE_DOCENTE" })
	@GetMapping("/activacion/status/{id}")
	public ResponseEntity<?> activarExamen(@PathVariable Long id){
		
		Optional<Examen> o = service.findById(id);
		
		Examen examenDb = o.get();
		
		if(examenDb.isEnabled()) {
			examenDb.setEnabled(false);
		}else {
			examenDb.setEnabled(true);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@Secured({ "ROLE_ADMIN","ROLE_ALUMNO","ROLE_DOCENTE" })
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
}
