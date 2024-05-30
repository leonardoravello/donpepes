package com.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entities.Comentario;
import com.tienda.entities.Producto;
import com.tienda.entities.Usuario;
import com.tienda.repositories.IComentarioDao;
import com.tienda.repositories.IProductoDao;
import com.tienda.repositories.IUsuarioDao;

@Service
public class ComentarioServiceImp implements IComentarioService {

	@Autowired
	private IComentarioDao comentarioDao;

	@Autowired
	private IProductoDao productoDao;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public List<Comentario> getComentarios() {
		return comentarioDao.findAll();
	}

	@Override
	public Comentario comentar(int idProducto, int idUsuario, Comentario comentario) {
		Producto p = productoDao.findById(idProducto).orElseThrow();
		Usuario u = usuarioDao.findById(idUsuario).get();
		comentario.setProducto(p);
		comentario.setUsuario(u);
		return comentarioDao.save(comentario);
	}

	@Override
	public List<Comentario> listarPorProducto(int id) {
		return comentarioDao.listarPorProducto(id);
	}

}
