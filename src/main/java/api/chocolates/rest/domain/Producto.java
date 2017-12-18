package api.chocolates.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "producto") // la tablase llama producto (hay una entidad de dominio llamada producto y una
							// tabla con el mismo nombre
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // estrategia de generación de id
	private Long id;

	@NotNull
	@Column(nullable = false) // hacer prueba
	private String nombre;

	@NotNull
	@Column(nullable = false) // hacer prueba
	private String descripcion;

	/**
	 * Cantidad de piezas disponibles en stock. TODO: asegurar no sea menor a 0
	 */
	@NotNull
	@Column(nullable = false)
	private Integer cantidad;

	/**
	 * nombre del archivo de su foto
	 */
	@NotNull
	@Column(nullable = false)
	private String foto;

	// -------------------------------------- accesores
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	// -------------------------------------- constructores

	public Producto() {
	}

	public Producto(String descripcion) {
		this.descripcion = descripcion;
	}

	public Producto(String nombre, String descripcion, int cantidad, String foto) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.foto = foto;
	}

	public Producto(String nombre, String descripcion, String cantidad, String foto) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = Integer.parseInt(cantidad);
		this.foto = foto;
	}

	// ------------------------------------------

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cantidad=" + cantidad
				+ ", foto=" + foto + "]";
	}

	public boolean vende(int cantidad) {
		boolean retorno = false;
		if (this.cantidad >= cantidad) { // si hay suficientes productos para poder realizar la venta.
			this.cantidad = this.cantidad - cantidad;
			retorno = true;
			System.out.println("se van a vender " + cantidad + this.toString()); // debug
		}
		return retorno;
	}

}
