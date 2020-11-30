package com.tutoriasitste.tutorias.residencia.app.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Usuario;
import com.tutoriasitste.tutorias.residencia.app.models.repository.UsuarioRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;

@Service
public class UsuarioServiceImpl extends CommonServiceImpl<Usuario, UsuarioRepository> implements UsuarioService{

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findUserRoleDocente() {
		return repository.findUserRoleDocente();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findUserRoleAlumno() {
		// TODO Auto-generated method stub
		return repository.findUserRoleAlumno();
	}

}
