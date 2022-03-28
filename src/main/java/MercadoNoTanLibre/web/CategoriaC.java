/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MercadoNoTanLibre.web;

import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.service.CategoriaService;
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
@RequestMapping("/categoriaC")
public class CategoriaC {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/agregarEditarCategoria")
    public String agregarEditarCategoria(Model model, @RequestParam(name = "categoria", required = false) Categoria categoria){
         log.info("Entrando en agregarEditarCategoria ");
         
          if (categoria == null) { // en caso de que sea null significa que es una categoria nueva
            categoria=new Categoria();
            model.addAttribute("categoria", categoria);
        } else { // en caso que no lo sea es que ya existe la categoria y lo que quiere hacer es editarlo	
            //categoria = categoriaService.encontrarCategoria(categoria);
            model.addAttribute("categoria", categoria);
        }
         
        return "agregarEditarCategoria";
    }
    
    
    @PostMapping("/guardarCategoria")
    public String guardarCategoria(@RequestParam(name = "file", required = false)MultipartFile imagen, Categoria categoria,RedirectAttributes flash,
            @RequestParam(name = "imagenVieja", required = false)MultipartFile imagenVieja,@RequestParam(name = "esNuevo") boolean esNuevo){
        log.info("Entrando en guardarProducto ");
        
        if(esNuevo || !imagen.isEmpty()){
             categoriaService.guardar(categoria, imagen);
        }else{          
            categoriaService.guardar(categoria, imagenVieja);
        }
        //PROBLEMA, la categoria es vieja pero quiero agregar nueva imagen ajajjaj ,mierda, se me agrega la vieja imagen queeee siempre sera null
        flash.addFlashAttribute("exito", "Categoria guardada!!");
        
        return "redirect:/";
    }
    
    
    @GetMapping("/listarCateEditar")
    public String listarCateEditar(Model model, @RequestParam(name = "pageNo") String pageNo, @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "sortDir") String sortDir, @RequestParam(name = "pageSize") String pageSize){
        
        Page<Categoria> pageCate=categoriaService.encontrarPaginated(Integer.parseInt(pageNo), Integer.parseInt(pageSize), sortField, sortDir);
        
        model.addAttribute("categorias", pageCate.getContent());
        model.addAttribute("paginasTotales", pageCate.getTotalPages());
        model.addAttribute("totalItems", pageCate.getTotalElements());
        model.addAttribute("paginaActual", pageNo);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        return "listarCateEditar";
    }
    
}
