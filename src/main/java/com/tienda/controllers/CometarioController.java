package com.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.entities.Comentario;
import com.tienda.services.IComentarioService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/usuario")
public class CometarioController {

	@Autowired
	private IComentarioService comentarioService;

	@PostMapping("/{idProducto}/comentario/{idUsuario}")
	public ResponseEntity<Comentario> comentar(@PathVariable int idProducto, @PathVariable int idUsuario,
			@RequestBody Comentario comentario) {

		return ResponseEntity.ok(comentarioService.comentar(idProducto, idUsuario, comentario));
	}

	
}
