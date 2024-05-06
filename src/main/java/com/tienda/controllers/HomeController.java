package com.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.entities.Producto;
import com.tienda.services.IProductoService;

@RestController
@RequestMapping("/api/home")
public class HomeController {
	
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping()
	public List<Producto> catalogo(){
		return productoService.getProductos();
	}

}
