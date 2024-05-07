package com.tienda.entities.listas;

import java.util.ArrayList;
import java.util.List;

import com.tienda.entities.Medida;

public class ListaMedidas {

	private List<Medida> listaMedidas = new ArrayList<>();

	public ListaMedidas() {
		listaMedidas.add(new Medida(1, "Kilo"));
		listaMedidas.add(new Medida(2, "Unidad"));
		listaMedidas.add(new Medida(3, "Six Pack"));
		listaMedidas.add(new Medida(4, "Docena"));
		listaMedidas.add(new Medida(5, "Caja 24 unidades"));
	}

	public List<Medida> getListaMedidas() {
		return listaMedidas;
	}

	public Medida find(int id) {
		for (int i = 0; i < listaMedidas.size(); i++) {
			Medida m = listaMedidas.get(i);
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

}
