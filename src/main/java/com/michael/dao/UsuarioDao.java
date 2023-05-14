package com.michael.dao;

import com.michael.domain.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioDao extends CrudRepository<Usuario, Integer>{

}
