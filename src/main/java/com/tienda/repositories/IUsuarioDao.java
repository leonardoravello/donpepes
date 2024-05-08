package com.tienda.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tienda.entities.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {

	public Optional<Usuario> findByCorreo(String correo);

}
