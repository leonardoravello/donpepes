package com.tienda.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.entities.Usuario;
import com.tienda.repositories.IUsuarioDao;

import jakarta.validation.Valid;

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
	public ResponseEntity<?> cliente(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Usuario usuarioNuevo = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			return validation(result);
		}

		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario.setEstado(true);
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

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, Object> errors = new HashMap<>();

		result.getFieldErrors().forEach(error -> {
			errors.put(error.getField(), "El campo " + error.getField()+" " + error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);

	}

}
