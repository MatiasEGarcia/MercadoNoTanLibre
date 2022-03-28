package MercadoNoTanLibre.service;
import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.domain.Producto;
import MercadoNoTanLibre.domain.ProductoCategoria;
import java.util.List;

public interface ProductoCategoriaService {
    
    public List<ProductoCategoria> listarProductoCategoria();
    
    public void guardar(ProductoCategoria productoCategoria);
    
    public void eliminar(ProductoCategoria productoCategoria);
    
    public ProductoCategoria encontrarProductoCategoria(ProductoCategoria productoCategoria);
    
    public void eliminarProductoCategoriaPorCategoria(Categoria categoria);
    
    public List<ProductoCategoria> encontrarProductoCategoriaPorProducto(Producto producto);
}
