package com.tienda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tienda.entities.Categoria;
import com.tienda.entities.Medida;
import com.tienda.entities.Producto;
import com.tienda.entities.Usuario;
import com.tienda.entities.listas.ListaMedidas;
import com.tienda.services.ICategoriaService;
import com.tienda.services.IProductoService;
import com.tienda.services.IUsuarioService;


@RestController
@RequestMapping("/api/home")
public class HomeController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private ICategoriaService categoriaService;


	@GetMapping()
	public List<Producto> catalogo() {
		return productoService.getProductos();
	}

	@GetMapping("/producto/detalles/{id}")
	public ResponseEntity<?> find(@PathVariable int id) {
		Optional<Producto> producto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en la BD");
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!producto.isPresent()) {
			response.put("mensaje", "El producto con ID " + id + " no existe en la BD");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(producto.get(), HttpStatus.OK);
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

	@PostMapping("/registrar")
	public ResponseEntity<?> cliente(@org.springframework.web.bind.annotation.RequestBody Usuario usuario) {
		Usuario usuarioNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {

			usuarioNuevo = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en la BD");
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cuenta creada Correctamente");
		response.put("usuario", usuarioNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public List<Usuario> getUsers() {
		return usuarioService.listar();
	}

	@GetMapping("/categorias/{categoria}")
	public ResponseEntity<?> categorizar(@PathVariable int categoria) {
		List<Producto> productos = productoService.getProductos(categoria);
		if (productos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No hay productos disponibles para la categoría especificada.");
		} else {
			return ResponseEntity.ok(productos);
		}
	}

	@GetMapping("/pagina/{page}")
	public Page<Producto> listar(@PathVariable Integer page) {
		return productoService.findAll(PageRequest.of(page, 2));
	}
	
	

}
