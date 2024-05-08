package com.tienda.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.entities.Rol;

@Repository
public interface IRolDao extends JpaRepository<Rol, Integer> {

	public Optional<Rol> findByNombre(String nombre);
}
