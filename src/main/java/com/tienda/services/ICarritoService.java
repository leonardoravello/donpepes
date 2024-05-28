package com.tienda.services;

import java.util.List;

import com.tienda.entities.Carrito;
import com.tienda.entities.ItemCarrito;

public interface ICarritoService {

	Carrito getCarritoByUsuario(int idUsuario);

	Carrito agregarItem(int idUsuario, int idProducto, int cantidad);

	Carrito quitarItem(int idUsuario, int idProducto);

	Carrito vaciarCarrito(int idUsuario);

	List<ItemCarrito> getItems(int idUsuario);
	
	int gtCantidadItems(int idUsuadio);

}
