package com.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.entities.Categoria;
import com.tienda.entities.Medida;
import com.tienda.entities.Producto;
import com.tienda.entities.listas.ListaMedidas;
import com.tienda.services.ICategoriaService;
import com.tienda.services.IProductoService;

@RestController
@RequestMapping("/api/home")
public class HomeController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping()
	public List<Producto> catalogo() {
		return productoService.getProductos();
	}

	@GetMapping("/medidas")
	public List<Medida> getLista() {
		return new ListaMedidas().getListaMedidas();
	}

	@GetMapping("/medidas/{id}")
	public Medida getLista(@PathVariable int id) {
		return new ListaMedidas().find(id);
	}

	@GetMapping("/categorias")
	public List<Categoria> getCategorias() {
		return categoriaService.findAll();
	}

}
