package com.tutoriasitste.tutorias.residencia.app.services;

import java.util.List;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Usuario;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonService;

public interface UsuarioService extends CommonService<Usuario> {
	public Usuario findByUsername(String username);
	public List<Usuario> findUserRoleDocente();
	public List<Usuario> findUserRoleAlumno();
}
