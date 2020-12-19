package com.tutoriasitste.tutorias.residencia.app.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Alumno;
import com.tutoriasitste.tutorias.residencia.app.models.entity.Archivo;
import com.tutoriasitste.tutorias.residencia.app.models.entity.Docente;
import com.tutoriasitste.tutorias.residencia.app.models.entity.Usuario;
import com.tutoriasitste.tutorias.residencia.app.services.UsuarioServiceImpl;
import com.tutoriasitste.tutorias.residencia.app.services.alumnos.AlumnoService;
import com.tutoriasitste.tutorias.residencia.app.services.archivos.ArchivoServiceImpl;
import com.tutoriasitste.tutorias.residencia.app.services.docentes.DocenteServiceImpl;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

	// *****************    si manda errores kitar los valid y binding***************
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private ArchivoServiceImpl archivoServiceImpl;
	
	@Autowired
	private DocenteServiceImpl docenteServiceImpl;
	
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE","ROLE_ALUMNO"})
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Alumno alumno,BindingResult result,
			@PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setMatricula(alumno.getMatricula());
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setCorreo(alumno.getCorreo());
		alumnoDb.setCarrera(alumno.getCarrera());
		
		alumnoDb.setTelefono(alumno.getTelefono());
		alumnoDb.setSemestre(alumno.getSemestre());
		alumnoDb.setPromAsistencia(alumno.getPromAsistencia());
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> findByNombreOrApellido(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
	
	//crear-alumno-usuario
	@Secured({"ROLE_ADMIN","ROLE_ALUMNO"})
	@PostMapping("/crear/alumno-usuario/{term}")
	public ResponseEntity<?> crearAlumno(@Valid @RequestBody Alumno alumno,BindingResult result,
			@PathVariable String term){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Usuario usuario = usuarioService.findByUsername(term);
       
		alumno.setUsuario(usuario);
        
		return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumno));
	}
	
	@Secured({"ROLE_ADMIN","ROLE_ALUMNO"})
	@GetMapping("/filtrar/alumno-usuario/{term}")
	public ResponseEntity<?> filtrarAlumnoDeUsuario(@PathVariable String term){

		return ResponseEntity.ok(service.findAlumnoByUsuarioUsername(term));
	}
    
	//crear-archivo
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/{id}/crear-archivo")
	public ResponseEntity<?>crearConArchivo(@Valid Archivo entity,BindingResult result,@PathVariable Long id,
			@RequestParam MultipartFile archivo)
			throws IOException {
		
		if (archivo != null) {
			entity.setArchivo(archivo.getBytes());
		}		
		
		
		Optional<Alumno> o = service.findById(id); // encontrar el alumno por id
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get(); // guardar el alumno en alumnoDb
		
		Usuario usuarioDb = alumnoDb.getUsuario();  //obtener el usuario del alumno
		System.out.println(entity.getId()); 
		entity.setUsuario(usuarioDb);  // en la entity archivo guardar el usuarioDb 
		return ResponseEntity.status(HttpStatus.CREATED).body(archivoServiceImpl.save(entity));
	}
		
	//ver-archivo
	@Secured({"ROLE_ADMIN","ROLE_ALUMNO"})
	@GetMapping("/ver-archivo/{id}")
	public ResponseEntity<?> verArchivo(@PathVariable Long id){
	
		return ResponseEntity.ok(archivoServiceImpl.findArchivoByUsuarioId(id));
	}

	// Buscar alumno por matricula
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<?> matricula(@PathVariable Long matricula){
	
		return ResponseEntity.ok(service.findByMatricula(matricula));
	}
	
	// Buscar docente por alumno
		@Secured({"ROLE_ADMIN", "ROLE_DOCENTE","ROLE_ALUMNO"})	
		@GetMapping("/filtrar-docente/alumno/{id}")
		public ResponseEntity<?> filtrarDocentePorALumno(@PathVariable Long id){
			return ResponseEntity.ok(docenteServiceImpl.findDocenteByStudent(id));
		}
}
