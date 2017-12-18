package api.chocolates.rest.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import api.chocolates.rest.domain.Producto;
import api.chocolates.rest.exceptions.StorageFileNotFoundException;
import api.chocolates.rest.services.ProductosService;
import api.chocolates.rest.services.StorageService;

@Controller
public class FileUploadController {
	
	private final StorageService storageService;
	
	@Autowired
	ProductosService productoService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

//    carga pagina antes de llenar formulario
//    original "/"
    @GetMapping("/agrega")
    public String listUploadedFiles(Model model) throws IOException {
//  debug inicio: muestra lista con los archivos
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        // debug fin
//template donde agrega las cosas del modelo
        return "agrega";  
    }

    // debug method
//    agrega al body de la página la lista con los archivos
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        System.out.println("url archivos: "+file.toString() );  //debug
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
// Se ejecuta en el submit del formulario para agregar producto
//    original "/" pero ya jalaba
    @PostMapping("/")
    public String handleFileUpload(
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("nombre") String nombre,
    		@RequestParam("descripcion") String descripcion,
    		@RequestParam("cantidad") String cantidad,
            RedirectAttributes redirectAttributes) {

    	String url_foto = "https://api-rest-chocolates.herokuapp.com/files/"+file.getOriginalFilename();
    	
    	
    	
    	
//    	crear objeto con los strings
    	Producto producto = new Producto(nombre, descripcion, cantidad, url_foto);
    	System.out.println(producto.toString());  //debug
    	
//    	guarda el archivo de la imagen
        storageService.store(file);
        
//agrega el producto como tal (al repo)
        productoService.insertarProducto(producto);
        System.out.println("Iinsertando producto...");  //debug
        
//        Atributos que se envian a la vista siguiente, puede servir para el alert
        redirectAttributes.addFlashAttribute("message", "Se ha agregado un producto  " + "nombre: " + nombre + " archivo: " + file.getOriginalFilename() + "!");

        return "redirect:/";  // pagina a donde vá después del submit e.g. "redirect:/" lleva a la raíz; 
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
