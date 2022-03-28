package MercadoNoTanLibre.service;

import java.util.List;

import MercadoNoTanLibre.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface CategoriaService {
    
    public List<Categoria> listarCategorias();
    
    public List<Categoria> listarCategoriasPorEstado(String estado);
    
    public Categoria guardar(Categoria categoria,MultipartFile imagen);
    
    public void eliminar(Categoria categoria);
    
    public Categoria encontrarCategoria(Categoria categoria);
    
    public Page<Categoria> encontrarPaginated(int pageNo, int pageSize, String sortField, String sortDir);
    
}
