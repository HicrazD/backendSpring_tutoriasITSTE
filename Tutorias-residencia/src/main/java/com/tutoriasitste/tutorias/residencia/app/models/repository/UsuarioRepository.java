package com.tutoriasitste.tutorias.residencia.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.tutoriasitste.tutorias.residencia.app.models.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u join fetch u.roles r where r.role='ROLE_DOCENTE'")
	public List<Usuario> findUserRoleDocente();
	
	@Query("select u from Usuario u join fetch u.roles r where r.role='ROLE_ALUMNO'")
	public List<Usuario> findUserRoleAlumno();

}
