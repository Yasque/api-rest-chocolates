package api.chocolates.rest.services;

import java.util.List;

import api.chocolates.rest.domain.Producto;

public interface ProductosService {
	
	List<Producto> obtenerProductos();
	
	void insertarProducto(Producto producto);

	String compraProducto(String id, int cantidad);

	void eliminaProducto(String id);
}
