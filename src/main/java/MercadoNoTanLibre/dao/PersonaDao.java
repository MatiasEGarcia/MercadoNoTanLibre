package MercadoNoTanLibre.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MercadoNoTanLibre.domain.Persona;


public interface PersonaDao extends JpaRepository<Persona,Long>{
	
}
