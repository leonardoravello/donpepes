package com.tienda.entities.listas;

import java.util.ArrayList;
import java.util.List;

import com.tienda.entities.Medidas;

public class ListaMedidas {

	private List<Medidas> listaMedidas = new ArrayList<>();

	public ListaMedidas() {
		listaMedidas.add(new Medidas(1, "Kilo"));
		listaMedidas.add(new Medidas(2, "Unidad"));
		listaMedidas.add(new Medidas(3, "Six Pack"));
		listaMedidas.add(new Medidas(4, "Docena"));
		listaMedidas.add(new Medidas(5, "Caja 24 unidades"));
	}

	public List<Medidas> getListaMedidas() {
		return listaMedidas;
	}

	public Medidas find(int id) {
		for (int i = 0; i < listaMedidas.size(); i++) {
			Medidas m = listaMedidas.get(i);
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

}
