package com.tutoriasitste.tutorias.residencia.app.controllers;


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
import org.springframework.web.bind.annotation.RestController;
import com.tutoriasitste.tutorias.residencia.app.models.entity.Role;
import com.tutoriasitste.tutorias.residencia.app.models.entity.Usuario;
import com.tutoriasitste.tutorias.residencia.app.services.RoleServiceImpl;
import com.tutoriasitste.tutorias.residencia.app.services.UsuarioService;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends CommonController<Usuario,UsuarioService> {
	
	// *****************    si manda errores kitar los valid y binding***************
	@Autowired
	public RoleServiceImpl roleService;
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario,BindingResult result,
			@PathVariable Long id){

		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Usuario> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Usuario usuarioDb = o.get();
		usuarioDb.setUsername(usuario.getUsername());
		usuarioDb.setPassword(usuario.getPassword());
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
	}

	@PostMapping("/usuario-role/{id}")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario,BindingResult result,
			@PathVariable Long id){

		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Role> r = roleService.findById(id); // We found the role_id
		if(r.isEmpty()) {                            // if role_id is Present, we continue
			return ResponseEntity.notFound().build();
		}
		
        Role roleNew = r.get();  // We save the role in a object(roleNew) of the class Role
		/*
     	Archivos o = new Archivos();   // We create a object (o) of type file of the class Files
		o.setNombre("New file");
		archivoService.save(o);
		       
        usuario.setArchivo(o);
        */
        usuario.addRol(roleNew);
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
	}
	
	
	@GetMapping("/filtrar/username/{term}")
	public ResponseEntity<?> filtrarUsuario(@PathVariable String term){
		return ResponseEntity.ok(service.findByUsername(term));
	}
	
	

	@GetMapping("/filtrar/usuarios-role-docente")
	public ResponseEntity<?> filtrarRoleDocente(){
		return ResponseEntity.ok(service.findUserRoleDocente());
	}
	
	@GetMapping("/filtrar/usuarios-role-alumno")
	public ResponseEntity<?> filtrarRoleAlumno(){
		return ResponseEntity.ok(service.findUserRoleAlumno());
	}

}
