package MercadoNoTanLibre.service;

import java.util.List;
import MercadoNoTanLibre.domain.Producto;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    
    public List<Producto> listarProductos();
    
    public void guardar(Producto producto,MultipartFile imagen);
    
    public void eliminar(Producto producto);
    
    public Producto encontrarProducto(Long  idProducto); 
    
    public Page<Producto> encontrarPaginated(int pageNo, int pageSize, String sortField, String sortDir);
    
    public void actualizarCategoDeProd(List<String> boxCategorias, Producto producto, boolean agregarOEditar);
}
