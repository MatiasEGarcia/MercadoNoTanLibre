package MercadoNoTanLibre.service;

import java.util.List;

import MercadoNoTanLibre.domain.Comentario;

public interface ComentarioService {

    public List<Comentario> listarComentario();
    
    public void guardar(Comentario comentario);
    
    public void eliminar(Comentario comentario);
    
    public Comentario encontrarComentario(Comentario comentario);
    
}
