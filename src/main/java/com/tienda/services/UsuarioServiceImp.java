package com.tienda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tienda.entities.Rol;
import com.tienda.entities.Usuario;
import com.tienda.repositories.IRolDao;
import com.tienda.repositories.IUsuarioDao;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	private IUsuarioDao usuarioDao;
	private IRolDao rolDao;
	private PasswordEncoder passwordEncoder;

	public UsuarioServiceImp(IUsuarioDao usuarioDao, PasswordEncoder passwordEncoder, IRolDao rolDao) {
		this.usuarioDao = usuarioDao;
		this.passwordEncoder = passwordEncoder;
		this.rolDao = rolDao;
	}

	@Override
	public Usuario save(Usuario usuario) {
		List<Rol> roles = new ArrayList<>();
		Optional<Rol> optRol = rolDao.findByNombre("ROLE_USER");
		optRol.ifPresent(rol -> roles.add(rol));
		usuario.setRoles(roles);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioDao.save(usuario);
	}

	@Override
	public List<Usuario> listar() {
		return (List<Usuario>) usuarioDao.findAll();
	}

}
