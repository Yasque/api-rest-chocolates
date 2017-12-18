package api.chocolates.rest.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import api.chocolates.rest.domain.Producto;
import api.chocolates.rest.domain.ProductosRepository;
import api.chocolates.rest.services.ProductosService;

@Controller
public class HomeController {

	@Autowired
	ProductosService productoService;

//	muestra los productos disponibles en stock
	//original /lista
	@RequestMapping("/")
	public String lista(Model model) {
		List<Producto> productos = productoService.obtenerProductos();
		// debug inicio
		System.out.println("productos:");
		for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
			Producto producto = (Producto) iterator.next();
			System.out.println(producto.toString());
		}
		// debug fin
		model.addAttribute("productos", productos);
		return "lista";
	}

}