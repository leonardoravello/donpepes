package com.tienda.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	@Column(unique = true)
	private String descripcion;
	private String ruta;
	private String inventoryStatus;
	private int estado;
	private int stock;
	@Column(name = "precio_venta")
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

	@OneToMany(mappedBy = "producto")
	@JsonManagedReference
	private List<Comentario> comentarios;

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

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Producto(int id, String nombre, String descripcion, String ruta, String inventoryStatus, int estado,
			int stock, double precioVenta, Marca marca, Categoria categoria, UnidadMedida medida) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ruta = ruta;
		this.inventoryStatus = inventoryStatus;
		this.estado = estado;
		this.stock = stock;
		this.precioVenta = precioVenta;
		this.marca = marca;
		this.categoria = categoria;
		this.medida = medida;
	}

	@PrePersist
	@PreUpdate
	public void inventoryStatusUpt() {
		if (stock >= 50) {
			inventoryStatus = "En Stock";
		} else if (stock < 50 && stock > 0) {
			inventoryStatus = "Poco Stock";
		} else if (stock <= 0) {
			inventoryStatus = "Sin Stock";
		}

	}

}
