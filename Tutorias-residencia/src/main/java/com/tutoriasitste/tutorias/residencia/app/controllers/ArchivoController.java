package com.tutoriasitste.tutorias.residencia.app.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.activation.MimeType;
import javax.swing.Spring;
import javax.validation.Valid;

import org.hibernate.validator.internal.metadata.aggregated.rule.ParallelMethodsMustNotDefineGroupConversionForCascadedReturnValue;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.tutoriasitste.tutorias.residencia.app.models.entity.Archivo;
import com.tutoriasitste.tutorias.residencia.app.services.archivos.ArchivoServices;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;
import com.zaxxer.hikari.pool.HikariProxyDatabaseMetaData;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api/archivos")
public class ArchivoController extends CommonController<Archivo, ArchivoServices> {

	//crear-archivo para admin
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/crear-archivo")
	public ResponseEntity<?> crearConArchivo(@Valid Archivo entity, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {

		if (!archivo.isEmpty()) {
			entity.setArchivo(archivo.getBytes());
		}
		System.out.print(archivo.getContentType());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
	}

	//editar-archivo
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})
	@PutMapping("/editar-archivo/{id}")
	public ResponseEntity<?> editarArchivo(@Valid Archivo entity, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException {

		Optional<Archivo> o = service.findById(id);

		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Archivo archivoDb = o.get();

		archivoDb.setNombre(entity.getNombre());
		archivoDb.setComentario(entity.getComentario());
		archivoDb.setTipo(entity.getTipo());

		if (!archivo.isEmpty()) {
			archivoDb.setArchivo(archivo.getBytes());
		}
		System.out.println(archivoDb.getArchivo());
		service.save(archivoDb);

		return ResponseEntity.status(HttpStatus.CREATED).body(archivoDb);
	}

	
	//editar-archivo
		@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})
		@PutMapping("/{id}")
		public ResponseEntity<?> editar(@Valid @RequestBody Archivo entity, BindingResult result, @PathVariable Long id)  {

			if(result.hasErrors()) {
				return this.validar(result);
			}
			
			Optional<Archivo> o = service.findById(id);

			if (o.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			Archivo archivoDb = o.get();

			archivoDb.setNombre(entity.getNombre());
			archivoDb.setComentario(entity.getComentario());
			archivoDb.setTipo(entity.getTipo());

			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(archivoDb));
		}
		
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})
	@GetMapping("/uploads/file-word/{id}")
	public ResponseEntity<?> verWord(@PathVariable Long id) {

		Optional<Archivo> o = service.findById(id);

		if (o.isEmpty() || o.get().getArchivo() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource pdf = new ByteArrayResource(o.get().getArchivo());

		System.out.print(o.get().getNombre());
		return ResponseEntity.ok()
				.contentType(MediaType
						.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
				.body(pdf);

	}
	
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})
	@GetMapping("/uploads/file-excel/{id}")
	public ResponseEntity<?> verExcel(@PathVariable Long id) {

		Optional<Archivo> o = service.findById(id);

		if (o.isEmpty() || o.get().getArchivo() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource pdf = new ByteArrayResource(o.get().getArchivo());

		System.out.print(o.get().getNombre());
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(pdf);

	}
	
	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE","ROLE_ALUMNO"})
	@GetMapping("/uploads/file-pdf/{id}")
	public ResponseEntity<?> verPdf(@PathVariable Long id) {

		Optional<Archivo> o = service.findById(id);

		if (o.isEmpty() || o.get().getArchivo() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource pdf = new ByteArrayResource(o.get().getArchivo());

		System.out.print(o.get().getNombre());
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf);
	}

	@Secured({"ROLE_ADMIN", "ROLE_DOCENTE"})
	@GetMapping("/tipo/{term}")
	public ResponseEntity<?> formato(@PathVariable String term) {
		return ResponseEntity.ok(service.findByTipo(term));
	}

}