package MercadoNoTanLibre.service;

import java.util.List;

import MercadoNoTanLibre.domain.Persona;


public interface PersonaService {
    
    public List<Persona> listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPersona(Persona persona);
}
