package com.tutoriasitste.tutorias.residencia.app.services;

import org.springframework.stereotype.Service;

import com.tutoriasitste.tutorias.residencia.app.models.entity.Role;
import com.tutoriasitste.tutorias.residencia.app.models.repository.RoleRepository;
import com.tutoriasitste.tutorias.residencia.commons.services.CommonServiceImpl;

@Service
public class RoleServiceImpl extends CommonServiceImpl<Role, RoleRepository> implements RoleService{

}
