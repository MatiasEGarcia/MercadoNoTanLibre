package MercadoNoTanLibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.Pedido;

public interface PedidoDao extends JpaRepository<Pedido, Long>{

}
