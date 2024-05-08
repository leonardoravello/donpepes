package com.tienda.services;

import java.util.List;

import com.tienda.entities.Usuario;

public interface IUsuarioService {

	public Usuario save(Usuario usuario);
	
	public List<Usuario> listar();
	
}
