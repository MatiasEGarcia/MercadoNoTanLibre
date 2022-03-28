package MercadoNoTanLibre.dao;

import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.ProductoCategoria;
import java.util.List;

public interface ProductoCategoriaDao extends JpaRepository<ProductoCategoria, Long>{

    //Eliminamos el productoCategoria por su categoria
    void deleteByCategoria(Categoria categoria);
    
    List<ProductoCategoria> findByProducto(Producto producto);
}
