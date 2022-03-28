package MercadoNoTanLibre.web;

import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.domain.Producto;
import MercadoNoTanLibre.domain.ProductoCategoria;

import MercadoNoTanLibre.service.CategoriaService;
import MercadoNoTanLibre.service.ProductoCategoriaService;
import MercadoNoTanLibre.service.ProductoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/productoC")
public class ProductoC {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    //aca vamos a averiguar si tenemos un producto nuevo o uno ya existente 
    @GetMapping("/agregarEditarProducto")
    public String paginaAgregarEditarProducto(Model model, @RequestParam(name = "producto", required = false) Producto producto) {//recibo el objeto Producto entero con todo sus valores
        log.info("Entrando en AgregarEditarProducto ");

        List<Categoria> categoriasActivas = categoriaService.listarCategoriasPorEstado("activo");
        List<Categoria> catesEnProdSelec = new ArrayList<>();
        

        if (producto == null) { // en caso de que sea null significa que es un producto nuevo
            producto = new Producto();
            model.addAttribute("producto", producto);
        } else { // en caso que no lo sea es que ya existe el producto y lo que quiere hacer es editarlo	
             Producto productoBd= productoService.encontrarProducto(producto.getIdProducto());
            catesEnProdSelec = producto.getCategorias();
            model.addAttribute("producto", producto);
            
            // si la categoria se encuentra dentro de catesEnProdSeleccionado entonces establecemos true en setEnProducto, esto es para marchar checked en la vista
            for(int i=0; i<categoriasActivas.size();i++){
                 categoriasActivas.get(i).setEnProducto(catesEnProdSelec.contains(categoriasActivas.get(i)));
            }
        }

        model.addAttribute("categoriasActivas", categoriasActivas);
       

        return "agregarEditarProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@RequestParam(name = "file", required = false) MultipartFile imagen,
            @RequestParam(name = "imagenVieja", required = false) MultipartFile imagenVieja,  
            @RequestParam(name="boxCategorias")List<String> boxCategorias, 
            @RequestParam(name = "esNuevo") boolean esNuevo,
            Producto producto,//aca el producto no me viene completo, por ejemplo viene sin sus categorias
            RedirectAttributes flash) {
        log.info("Entrando en guardarProducto");

        
        
        if (esNuevo || !imagen.isEmpty()) {
            productoService.guardar(producto, imagen);
            
        } else {
            productoService.guardar(producto, imagenVieja);
           
        }
        
       
        productoService.actualizarCategoDeProd(boxCategorias, producto , esNuevo);

        flash.addFlashAttribute("exito", "Producto actualizado!!");

        return "redirect:/";
    }

   

    @GetMapping("/listarProdEditar")
    public String listarProdEditar(Model model, @RequestParam(name = "pageNo") String pageNo, @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "sortDir") String sortDir, @RequestParam(name = "pageSize") String pageSize) {
        log.info("Entrando en paginaProdEditar");
        

        Page<Producto> pageProd = productoService.encontrarPaginated(Integer.parseInt(pageNo), Integer.parseInt(pageSize), sortField, sortDir);
        
        model.addAttribute("productos", pageProd.getContent());
        model.addAttribute("paginasTotales", pageProd.getTotalPages());
        model.addAttribute("totalItems", pageProd.getTotalElements());
        model.addAttribute("paginaActual", pageNo);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "listarProdEditar";
    }

    
}
