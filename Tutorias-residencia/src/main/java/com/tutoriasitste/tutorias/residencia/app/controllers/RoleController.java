package com.tutoriasitste.tutorias.residencia.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Role;
import com.tutoriasitste.tutorias.residencia.app.services.RoleService;
import com.tutoriasitste.tutorias.residencia.common.controllers.CommonController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/roles")
public class RoleController extends CommonController<Role, RoleService>{

}
