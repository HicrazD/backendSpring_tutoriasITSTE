package com.tutoriasitste.tutorias.residencia.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findByNombre(String term);
}
