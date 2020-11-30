package com.tutoriasitste.tutorias.residencia.app.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.tutoriasitste.tutorias.residencia.app.services.archivos.ArchivoServiceImpl;
import com.tutoriasitste.tutorias.residencia.app.services.docentes.DocenteService;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/docentes")
public class DocenteController extends CommonController<Docente, DocenteService> {
	// *****************    si manda errores kitar los valid y binding***************
	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private ArchivoServiceImpl archivoServiceImpl;

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Docente docente,BindingResult result,
			@PathVariable Long id) {
        
		if(result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Docente> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Docente docenteDb = o.get();
		docenteDb.setNombre(docente.getNombre());
		docenteDb.setApellido(docente.getApellido());
		docenteDb.setCorreo(docente.getCorreo());
		docenteDb.setDivision(docente.getDivision());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(docenteDb));
	}

	@PostMapping("/crear/docente-usuario/{term}")
	public ResponseEntity<?> crearDocente(@Valid @RequestBody Docente docente,BindingResult result,
			@PathVariable String term) {
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Usuario usuario = usuarioService.findByUsername(term);

		docente.setUsuario(usuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(docente));
	}

	@PostMapping("/{id}/crear-archivo")
	public ResponseEntity<?> crearEnUsuario(@Valid Archivo entity,BindingResult result,@PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException {

		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		if (!archivo.isEmpty()) {
			entity.setArchivo(archivo.getBytes());
		}

		Optional<Docente> o = service.findById(id); // encontrar el docente por id
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Docente docenteDb = o.get(); // guardar el docente en docenteDb

		Usuario usuarioDb = docenteDb.getUsuario(); // obtener el usuario del docente
		entity.setUsuario(usuarioDb); // en la entity archivo guardar el usuarioDb
		return ResponseEntity.status(HttpStatus.CREATED).body(archivoServiceImpl.save(entity));
	}

	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
		Optional<Docente> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Docente docenteDb = o.get();

		alumnos.forEach(a -> {
			docenteDb.addAlumno(a);
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(docenteDb));
	}

	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Docente> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Docente docenteDb = o.get();

		docenteDb.removeAlumno(alumno);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(docenteDb));
	}

	@GetMapping("/alumnos/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
		Docente docente = service.findDocenteByStudent(id);
		return ResponseEntity.ok(docente);
	}

	@GetMapping("/filtrar/docente-usuario/{term}")
	public ResponseEntity<?> filtrarDocenteDeUsuario(@PathVariable String term) {
		return ResponseEntity.ok(service.findDocenteByUsuarioUsername(term));
	}

	@GetMapping("/filtrar/nombreOrapellido/{term}")
	public ResponseEntity<?> findNombreOrApellido(@PathVariable String term) {

		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
	
	// Encontrar por division = division  and nombre or apellido
	@GetMapping("/filtrar/division/{division}/nombreOrapellido/{term}")
	public ResponseEntity<?> findDvisionNombreOrApellido(@PathVariable String division,@PathVariable String term) {

		return ResponseEntity.ok(service.findByDivisionIsc(division,term));
	}
	

	@GetMapping("/ver-archivo/{id}")
	public ResponseEntity<?> verArchivo(@PathVariable Long id) {

		return ResponseEntity.ok(archivoServiceImpl.findArchivoByUsuarioId(id));
	}

}
