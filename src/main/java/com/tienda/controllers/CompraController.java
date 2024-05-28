package com.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.entities.Carrito;
import com.tienda.entities.ItemCarrito;
import com.tienda.entities.Orden;

import com.tienda.services.ICarritoService;
import com.tienda.services.IItemCarritoService;
import com.tienda.services.IProductoService;
import com.tienda.services.IVentaService;

@Controller
@RequestMapping("/api/home")
public class CompraController {

	private IVentaService ventaService;

	private IProductoService productoService;

	private IItemCarritoService itemCarritoService;

	private ICarritoService carritoService;



	public CompraController(IVentaService ventaService, IProductoService productoService,
			IItemCarritoService itemCarritoService, ICarritoService carritoService) {
		this.ventaService = ventaService;
		this.productoService = productoService;
		this.itemCarritoService = itemCarritoService;
		this.carritoService = carritoService;
	}

	@GetMapping("/getNum")
	public ResponseEntity<String> getNum() {
		return ResponseEntity.ok(ventaService.generarNumVenta());
	}

	@PostMapping("/add-item")
	public void agregar(@RequestParam int cantidad, @RequestParam int idProducto) {
		ItemCarrito linea = new ItemCarrito();
		linea.setCantidad(5);

	}

	@GetMapping("/carrito/{id}")
	public ResponseEntity<Carrito> getCarrito(@PathVariable int id) {
		Carrito c = carritoService.getCarritoByUsuario(id);
		return ResponseEntity.ok(c);
	}

	@PostMapping("/add/producto/{idProducto}")
	public ResponseEntity<Carrito> agregarItem(@RequestParam int userId, @PathVariable int idProducto,
			@RequestParam int cantidad) {

		return ResponseEntity.ok(carritoService.agregarItem(userId, idProducto, cantidad));
	}

	@GetMapping("/cantidad")
	public ResponseEntity<Integer> getCantidadItems(@RequestParam int id) {

		return ResponseEntity.ok(carritoService.gtCantidadItems(id));
	}

	@DeleteMapping("/remove/{codProducto}")
	public ResponseEntity<Carrito> eliminarLinea(@RequestParam int idUsuario, @PathVariable int codProducto) {
		return ResponseEntity.ok(carritoService.quitarItem(idUsuario, codProducto));
	}
}
