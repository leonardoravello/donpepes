package com.tienda.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.entities.Orden;
import com.tienda.repositories.IVentaDao;

@Service
public class VentaServiceImp implements IVentaService {

	private IVentaDao ventaDao;

	public VentaServiceImp(IVentaDao ventaDao) {
		this.ventaDao = ventaDao;
	}

	@Override
	public String generarNumVenta() {
		long num = 0;
		String numBoleta = "";

		List<Orden> ordenes = findAllVentas();
		List<Long> numerosBoleta = new ArrayList<>();

		ordenes.stream().forEach(o -> numerosBoleta.add(Long.valueOf(o.getNumeroBoleta())));

		if (ordenes.isEmpty()) {
			num = 1;
		} else {
			num = numerosBoleta.stream().max(Long::compare).get();
			num++;
		}

		if (num < 10) {
			numBoleta = "VEN-000000" + String.valueOf(num);
		} else if (num < 100) {
			numBoleta = "VEN-00000" + String.valueOf(num);
		} else if (num < 1000) {
			numBoleta = "VEN-0000" + String.valueOf(num);
		} else if (num < 10000) {
			numBoleta = "VEN-000" + String.valueOf(num);
		} else if (num < 100000) {
			numBoleta = "VEN-00" + String.valueOf(num);
		}

		return numBoleta;
	}

	@Override
	public List<Orden> findAllVentas() {
		return ventaDao.findAll();
	}

}
