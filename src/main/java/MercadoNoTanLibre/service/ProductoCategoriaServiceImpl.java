package MercadoNoTanLibre.service;

import MercadoNoTanLibre.dao.ProductoCategoriaDao;
import MercadoNoTanLibre.domain.Categoria;
import MercadoNoTanLibre.domain.Producto;
import MercadoNoTanLibre.domain.ProductoCategoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoCategoriaServiceImpl implements ProductoCategoriaService {

    @Autowired
    private ProductoCategoriaDao productoCategoriaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<ProductoCategoria> listarProductoCategoria() {
        return (List<ProductoCategoria>)productoCategoriaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(ProductoCategoria productoCategoria) {
        productoCategoriaDao.save(productoCategoria);
    }

    @Override
    @Transactional
    public void eliminar(ProductoCategoria productoCategoria) {
        productoCategoriaDao.delete(productoCategoria);
    }

    @Override
    @Transactional
    public ProductoCategoria encontrarProductoCategoria(ProductoCategoria productoCategoria) {
        return productoCategoriaDao.findById(productoCategoria.getIdProductoCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarProductoCategoriaPorCategoria(Categoria categoria) {
        productoCategoriaDao.deleteByCategoria(categoria);
    }

    @Override
    public List<ProductoCategoria> encontrarProductoCategoriaPorProducto(Producto producto) {
        return productoCategoriaDao.findByProducto(producto);
    }
    
    

}
