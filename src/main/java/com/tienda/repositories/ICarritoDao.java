package com.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tienda.entities.Carrito;


@Repository
public interface ICarritoDao extends JpaRepository<Carrito, Integer> {

	@Query("select c from Carrito c where c.usuario.id=?1")
	public Carrito findByUsuario(int idUsuario);
}
