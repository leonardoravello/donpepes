package com.tienda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	@Column( unique = true)
	private String descripcion;
	private String ruta;
	private String inStock;
	private int estado;
	private int stock;
	@Column(name="precio_venta")
	private double precioVenta;
	@ManyToOne
	@JoinColumn(name = "id_marc")
	private Marca marca;
	@ManyToOne
	@JoinColumn(name = "id_cate")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "id_unid_medi")
	private UnidadMedida medida;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public UnidadMedida getMedida() {
		return medida;
	}

	public void setMedida(UnidadMedida medida) {
		this.medida = medida;
	}
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String foto) {
		this.ruta = foto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Producto() {

	}
	
	

	public String getInStock() {
		return inStock;
	}

	public void setInStock(String inStock) {
		this.inStock = inStock;
	}

	public Producto(int id, String nombre, String descripcion, String ruta, String inStock, int estado, int stock,
			double precioVenta, Marca marca, Categoria categoria, UnidadMedida medida) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ruta = ruta;
		this.inStock = inStock;
		this.estado = estado;
		this.stock = stock;
		this.precioVenta = precioVenta;
		this.marca = marca;
		this.categoria = categoria;
		this.medida = medida;
	}


	
	



}
