package MercadoNoTanLibre.service;

import MercadoNoTanLibre.dao.ComentarioDao;
import MercadoNoTanLibre.domain.Comentario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private ComentarioDao comentarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Comentario> listarComentario() {
        return (List<Comentario>)comentarioDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Comentario comentario) {
        comentarioDao.save(comentario);
    }

    @Override
    @Transactional
    public void eliminar(Comentario comentario) {
        comentarioDao.delete(comentario);
    }

    @Override
    @Transactional(readOnly=true)
    public Comentario encontrarComentario(Comentario comentario) {
        return comentarioDao.findById(comentario.getIdComentario()).orElse(null);
    }

}
