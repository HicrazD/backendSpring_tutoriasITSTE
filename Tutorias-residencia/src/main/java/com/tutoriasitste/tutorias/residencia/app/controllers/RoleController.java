package com.tutoriasitste.tutorias.residencia.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Role;
import com.tutoriasitste.tutorias.residencia.app.services.RoleService;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping("/api/roles")
public class RoleController extends CommonController<Role, RoleService>{
	
	@GetMapping("/filtrar/role-nombre/{term}")
	public ResponseEntity<?> filtrarRoleNombre(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@PostMapping("/create/roles")
	public ResponseEntity<?> createRoles(){
	
	Role roleAdmin = new Role();
	Role roleAlumno = new Role();
	Role roleDocente = new Role();
	
	roleAdmin.setNombre("ROLE_ADMIN");
	service.save(roleAdmin);
	roleAlumno.setNombre("ROLE_ALUMNO");
	service.save(roleAlumno);
	roleDocente.setNombre("ROLE_DOCENTE");
	service.save(roleDocente);
	return ResponseEntity.status(HttpStatus.CREATED).body(roleAdmin);
	}
	
}
