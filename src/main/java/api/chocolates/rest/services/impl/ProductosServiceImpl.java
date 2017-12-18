package api.chocolates.rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.chocolates.rest.domain.Producto;
import api.chocolates.rest.domain.ProductosRepository;
import api.chocolates.rest.services.ProductosService;

@Service // sin este no lo reconoce como un bean / capa de servicio2
public class ProductosServiceImpl implements ProductosService {

	// inyección de dependencias a travéz del autowire
	@Autowired
	ProductosRepository productoRepository;

	@Override
	public List<Producto> obtenerProductos() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public void insertarProducto(Producto producto) {
		productoRepository.save(producto);
	}

	// TODO mejorar para que haga update sin necesidad de delete
	@Override
	public String compraProducto(String id, int cantidad) {
		String retorno = "Compra fallida";
		Long id2 = Long.parseLong(id);
		Producto productoAux = productoRepository.findOne(id2);
		if (productoAux.vende(cantidad)) {
			productoRepository.delete(id2);
			productoRepository.save(productoAux);
			retorno = "Compra exitosa";
		}
		return retorno;
	}

	@Override
	public void eliminaProducto(String id) {
		Long id2 = Long.parseLong(id);
		productoRepository.delete(id2);
	}

}
