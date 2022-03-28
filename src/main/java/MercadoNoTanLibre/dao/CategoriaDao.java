package MercadoNoTanLibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.Categoria;
import java.util.List;


public interface CategoriaDao extends JpaRepository<Categoria, Long>{

    //buscamos las categorias que tengan un determinado estado
    List<Categoria> findByEstado(String estado);
    
}
