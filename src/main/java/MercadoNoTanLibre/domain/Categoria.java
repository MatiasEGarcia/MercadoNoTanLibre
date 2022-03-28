package MercadoNoTanLibre.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name="categorias")
public class Categoria implements Serializable {

    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Long idCategoria;
    
    @NotNull
    @Column(name="nombre")
    private String nombre;
    
    @NotNull
    @Column(name="estado")
    private String estado;
    
    //@Lob
    @Column(name="imagen")
    private String imagen;
    
    @OneToMany(fetch =FetchType.LAZY , mappedBy = "categoria")
    private List<ProductoCategoria> productoCategorias;

    @Transient
    private boolean enProducto;
    
    
    public Categoria(){
        
    }
    
    public Categoria(long idCategoria) {
        this.idCategoria=idCategoria;
    }

    
    
   
}
