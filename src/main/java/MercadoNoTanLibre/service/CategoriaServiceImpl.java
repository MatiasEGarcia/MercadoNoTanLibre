package MercadoNoTanLibre.service;

import MercadoNoTanLibre.dao.CategoriaDao;
import MercadoNoTanLibre.domain.Categoria;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDao categoriaDao;
    
    
    @Override
    @Transactional(readOnly=true)
    public List<Categoria> listarCategorias() {
        return (List<Categoria>)categoriaDao.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Categoria> listarCategoriasPorEstado(String estado) {
        return categoriaDao.findByEstado(estado);
        
    }

    @Override
    @Transactional
    public Categoria guardar(Categoria categoria,MultipartFile imagen) {      
        
         if (!imagen.isEmpty()) {
             try {
                 categoria.setImagen(Base64.getEncoder().encodeToString(imagen.getBytes()));
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         } else {
         }
        
        
        categoriaDao.save(categoria);
        log.info("Categoria guardada");
        return categoria;
    }

    @Override
    @Transactional
    public void eliminar(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    @Transactional(readOnly=true)
    public Categoria encontrarCategoria(Categoria categoria) {
       return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public Page<Categoria> encontrarPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
         Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        
        //Pageable provee la info para la paginacion
        Pageable pageable=PageRequest.of(pageNo-1,pageSize,sort); //la paginacion empieza en 1 por eso le resto 1
        
        //si solo quisiera usar el sort, tendria que pasar en vez del pageable, solo el sort
        return this.categoriaDao.findAll(pageable);
    }

    

}
