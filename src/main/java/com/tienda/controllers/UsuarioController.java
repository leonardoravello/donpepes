package com.tienda.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.entities.Usuario;
import com.tienda.repositories.IUsuarioDao;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mantenimiento")
public class UsuarioController {

	private IUsuarioDao usuarioDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioController(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> cliente(@RequestBody Usuario usuario) {
		Usuario usuarioNuevo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioNuevo = usuarioDao.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en la BD");
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cuenta creada Correctamente");
		response.put("categoria", usuarioNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
