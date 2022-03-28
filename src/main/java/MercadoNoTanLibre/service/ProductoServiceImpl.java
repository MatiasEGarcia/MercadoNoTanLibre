package MercadoNoTanLibre.service;

import MercadoNoTanLibre.dao.ProductoDao;
import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.domain.Producto;
import MercadoNoTanLibre.domain.ProductoCategoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private ProductoCategoriaService productoCategoriaService;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Producto producto, MultipartFile imagen) {

        try {
            producto.setImagen(Base64.getEncoder().encodeToString(imagen.getBytes()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        productoDao.save(producto);
        log.info("Producto guardado");
    }

    @Override
    @Transactional
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto encontrarProducto(Long idProducto) {
        return productoDao.findById(idProducto).orElse(null);
    }

    @Override
    @Transactional
    public Page<Producto> encontrarPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        //Pageable provee la info para la paginacion
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); //la paginacion empieza en 1 por eso le resto 1

        //si solo quisiera usar el sort, tendria que pasar en vez del pageable, solo el sort
        return this.productoDao.findAll(pageable);
    }

    @Override
    @Transactional
    public void actualizarCategoDeProd(List<String> boxCategorias, Producto producto, boolean esNuevo) {

        if (!esNuevo) {
            List<String> checkboxYaEnProducto = new ArrayList<>();
            List<ProductoCategoria> productoCategorias=productoCategoriaService.encontrarProductoCategoriaPorProducto(producto);
            List<Categoria> catesEnProdSelec = productoCategorias.stream().map(productoCategoria->productoCategoria.getCategoria()).collect(Collectors.toList());

            //si el boxCategorias ya se encuentra en la bdd asociado al producto(catesEnProdSelec) lo agregamos a checkboxYaEnProducto para borrarlo despues
            for (int i = 0; i < boxCategorias.size(); i++) {
                if (catesEnProdSelec.contains(new Categoria(Long.parseLong(boxCategorias.get(i))))) {
                    checkboxYaEnProducto.add(boxCategorias.get(i));
                }
            }

            //si la categoria que esta asociado al producto en la bdd(catesEnProdSelec) no esta dentro de los boxCategorias se borra de la bdd
            for (int i = 0; i < catesEnProdSelec.size(); i++) {
                if (!boxCategorias.contains(String.valueOf(catesEnProdSelec.get(i).getIdCategoria()))) {
                    productoCategoriaService.eliminarProductoCategoriaPorCategoria(catesEnProdSelec.get(i));
                }
            }

            //Quitamos de boxCategorias las categorias que ya estan asociadas al producto en la bdd
            if (!checkboxYaEnProducto.isEmpty()) {
                boxCategorias.removeAll(checkboxYaEnProducto);
            }

        }

        //Agregamos la asociacion entre el producto y cada una de las nuevas categorias
        for (int i = 0; i < boxCategorias.size(); i++) {
            ProductoCategoria productoCategoria = new ProductoCategoria();
            productoCategoria.setCategoria(new Categoria(Long.parseLong(boxCategorias.get(i))));
            productoCategoria.setProducto(producto);
            productoCategoriaService.guardar(productoCategoria);
        }

    }
    
   

}
