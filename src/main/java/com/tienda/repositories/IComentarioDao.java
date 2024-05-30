package com.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tienda.entities.Comentario;

@Repository
public interface IComentarioDao extends JpaRepository<Comentario, Integer> {
	
	@Query("select c from Comentario c where c.producto.id=?1")
	public List<Comentario> listarPorProducto(int id);

}
