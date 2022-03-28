package MercadoNoTanLibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.Usuario;


public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
}
