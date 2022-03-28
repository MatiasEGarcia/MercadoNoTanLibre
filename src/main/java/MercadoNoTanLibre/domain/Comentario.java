package MercadoNoTanLibre.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="Comentarios")
public class Comentario implements Serializable{

    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_comentario")
    private Long idComentario;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_producto") //referencia a id_producto
    private Producto producto;
    
    @NotNull
    @Column(name="comentario")
    private String comentario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_comentario")
    private Date fechaComentario;
    
    
    
            
}
