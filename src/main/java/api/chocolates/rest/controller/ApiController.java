package api.chocolates.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.chocolates.rest.domain.Mensaje;
import api.chocolates.rest.domain.Producto;
import api.chocolates.rest.domain.Saludo;
import api.chocolates.rest.services.ProductosService;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api") // (value="/api")es a nivel de clase y se invoca con
						// http://localhost:8080/api/saludar
public class ApiController {

	@Autowired
	ProductosService productoService;

	
	/**
	 * Agrega nuevo producto al stock.
	 * No recibe ID porque es auto-incrementado
	 */
	@PostMapping("/addproducto")
	public void agregar(
			@RequestParam(value = "nombre") String nom, 
			@RequestParam(value = "descripcion") String desc,
			@RequestParam(value = "cantidad") int cant, 
			@RequestParam(value = "foto") String foto) {
		Producto producto = new Producto(nom, desc, cant, foto);
		productoService.insertarProducto(producto);
	}

	@GetMapping("/getproductos")
	public HashMap<String, List<Producto>> objenerProductos() {
		List<Producto> retorno = productoService.obtenerProductos();
		HashMap<String, List<Producto>> x = new HashMap<>();
		x.put("productos", retorno);

		return x;
	}
	

	@PostMapping("/comprar")
	public Mensaje comprar(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "cantidad") int cant) {

		String mensaje = productoService.compraProducto(id, cant);
		
		Mensaje mensaje2 = new Mensaje(mensaje);
	
		return mensaje2;
	}
	

}