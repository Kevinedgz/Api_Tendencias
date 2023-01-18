package com.edu.proyect.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.edu.proyect.User.model.Usuario;
import com.edu.proyect.User.repository.UsuarioRepo;
@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioService{
	  @Autowired
	    UsuarioRepo userR;


    @Override
    public CrudRepository<Usuario, Integer> getDao() {
        return userR;
    }


	
}
