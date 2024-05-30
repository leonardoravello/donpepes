package com.tienda.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.entities.Carrito;
import com.tienda.entities.ItemCarrito;
import com.tienda.entities.Producto;
import com.tienda.repositories.ICarritoDao;
import com.tienda.repositories.IItemCarritoDao;
import com.tienda.repositories.IProductoDao;
import com.tienda.repositories.IUsuarioDao;

@Service
public class CarritoServiceImp implements ICarritoService {

	@Autowired
	private ICarritoDao carritoDao;

	@Autowired
	private IProductoDao productoDao;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IItemCarritoDao itemCarritoDao;

	@Override
	public Carrito getCarritoByUsuario(int idUsuario) {
		Carrito c = carritoDao.findByUsuario(idUsuario);
		if (c == null) {
			c = new Carrito();
			c.setUsuario(usuarioDao.findById(idUsuario).orElseThrow());
			carritoDao.save(c);
		}
		return c;
	}

	@Override
	public Carrito agregarItem(int idUsuario, int idProducto, int cantidad) {
		Carrito c = carritoDao.findByUsuario(idUsuario);
		if (c == null) {
			c = new Carrito();
			c.setUsuario(usuarioDao.findById(idUsuario).orElseThrow());
			carritoDao.save(c);
		}
		Optional<ItemCarrito> itemRepetido = c.getItems().stream()
				.filter(item -> item.getProducto().getId() == idProducto).findFirst();

		if (itemRepetido.isPresent()) {
			ItemCarrito item = itemRepetido.get();
			item.setCantidad(item.getCantidad() + cantidad);
			item.setImporte(item.getCantidad() * item.getProducto().getPrecioVenta());
			itemCarritoDao.save(item);
			c.setTotal(c.getItems().stream().mapToDouble(ln -> ln.getImporte()).sum());
		} else {

			Producto p = productoDao.findById(idProducto).orElseThrow();
			ItemCarrito linea = new ItemCarrito();
			linea.setCantidad(cantidad);
			linea.setProducto(p);
			linea.setImporte(cantidad * p.getPrecioVenta());
			linea.setCarrito(c);
			itemCarritoDao.save(linea);
			c.getItems().add(linea);
			c.setTotal(c.getItems().stream().mapToDouble(ln -> ln.getImporte()).sum());
			carritoDao.save(c);
		}

		return c;
	}

	@Override
	public Carrito quitarItem(int idUsuario, int item) {
		Carrito c = carritoDao.findByUsuario(idUsuario);
		ItemCarrito itemAQuitar = c.getItems().get(item);

		if (itemAQuitar != null) {
			c.getItems().remove(itemAQuitar);
			itemCarritoDao.deleteById(itemAQuitar.getId());
			carritoDao.save(c);
		}
		c.setTotal(c.getItems().stream().mapToDouble(ln -> ln.getImporte()).sum());
		return c;
	}

	@Override
	public Carrito vaciarCarrito(int idUsuario) {
		Carrito c = carritoDao.findByUsuario(idUsuario);
		c.getItems().clear();
		return c;
	}

	@Override
	public List<ItemCarrito> getItems(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int gtCantidadItems(int idUsuadio) {
		Carrito c = carritoDao.findByUsuario(idUsuadio);
		List<ItemCarrito> items = c.getItems();
		return items.size();
	}

}
