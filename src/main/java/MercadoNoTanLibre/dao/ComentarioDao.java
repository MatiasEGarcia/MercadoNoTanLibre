package MercadoNoTanLibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.Comentario;

public interface ComentarioDao extends JpaRepository<Comentario, Long> {

}
