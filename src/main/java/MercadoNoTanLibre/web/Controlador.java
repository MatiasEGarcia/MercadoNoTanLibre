/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MercadoNoTanLibre.web;

import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.service.CategoriaService;
import MercadoNoTanLibre.domain.Producto;
import MercadoNoTanLibre.service.ProductoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class Controlador {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Ejecutando inicio");

        Producto producto= new Producto(Long.parseLong("8"));
        Producto productoBd= productoService.encontrarProducto(producto.getIdProducto());
        /*Page<Producto> pageProd = null;
        Page<Categoria> pageCate = null;
        List<Producto> productosConOferta=null;  //Solo quiero diez para mostrar en el inicio
        List<Producto> productos=null;
        int contador=0;
        
        String sortFieldProd="idProducto";
        String sortFieldCate="idCategoria";
        String sortDir="asc";
        int pageNo=1;

        pageProd = productoService.encontrarPaginated(pageNo, 8,sortFieldProd, sortDir);
        pageCate = categoriaService.encontrarPaginated(pageNo, 8,sortFieldCate , sortDir);
        productos= pageProd.getContent();
        
        for(int i=0; i<productos.size();i++){      
            if(productos.get(i).getPrecioDescuento() == 0){
                continue;
            }else{
                productosConOferta.add(productos.get(i));
            }
            if(productosConOferta.size()==10){
                break;
            }          
        }


        model.addAttribute("productos", pageProd.getContent()); 
        model.addAttribute("paginasTotalesProd", pageProd.getTotalPages());
        model.addAttribute("totalItemsProd", pageProd.getTotalElements());
        model.addAttribute("sortFieldPrd", sortFieldProd);
        model.addAttribute("productosConOferta", productosConOferta);

        model.addAttribute("categorias", pageCate.getContent());
        model.addAttribute("paginasTotalesCate", pageCate.getTotalPages());
        model.addAttribute("totalItemsCate", pageCate.getTotalElements());
        model.addAttribute("sortFieldCate", sortFieldCate);
        
        model.addAttribute("paginaActual",pageNo);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        log.info("Enviando al index");*/
        return "index";
    }
    
    
    
    

    @GetMapping("/login")
    public String iniciarSesion() {
        log.info("Ejecutando la pagina de Inicio de Sesion");

        return "login";
    }

    @GetMapping("/crearCuenta")
    public String crearCuenta() {
        log.info("Ejecutando la pagina de Crear Cuenta");

        return "crearCuenta";
    }

    /*Esta retorna el crearCuenta tambien pero manda a esa pagina con los datos actuales del (Persona)usuario mas una variable para indicar que 
    hay campos que hay que prohibir la modificacion como el nombre de usuario*/
    @GetMapping("/modificarCuenta")
    public String modificarCuenta() {
        return "crearCuenta";
    }

    /*Al lado de definir Cuenta podria poner un objeto perosna o usuario, en el controlador si esta lleno lo mandamos a modificar,
                                si esta vacio lo mandamos a crear*/
    @GetMapping("/definirCuenta")
    public String definirCuenta(@PathVariable String decision) {

        switch (decision) {
            case "crear":
                log.info("Ejecutando el crear cuenta");
                break;
            case "modificar":
                log.info("Ejecutando el modificar cuenta");
                break;

        }

        return "crearCuenta";

    }

    @GetMapping("/detalleProducto")/*en teoria aca tendria que enviarse con el id del producto*/
    public String detalleProducto() {
        log.info("Ejecutando la pagina de Detalle producto");

        return "detalleProducto";
    }

    @GetMapping("/favCarCompras")
    public String favCarCompras() {
        log.info("Ejecutando la pagina del favCarCompras");

        return "favCarCompras";
    }

}
