package MercadoNoTanLibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.Producto;


public interface ProductoDao extends JpaRepository<Producto, Long>{

    
    
}
