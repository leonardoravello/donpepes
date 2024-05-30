package com.tienda.services;

import java.util.List;

import com.tienda.entities.Comentario;

public interface IComentarioService {

	public List<Comentario> getComentarios();

	public Comentario comentar(int idProducto, int idUsuario, Comentario comentario);
	public List<Comentario> listarPorProducto(int id);

}
