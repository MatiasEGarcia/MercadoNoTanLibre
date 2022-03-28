

package MercadoNoTanLibre.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="valorizacion")
public class Valorizacion implements Serializable{

    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_valorizacion")
    private int idValorizacion;
    
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
    
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_producto") //referencia a id_producto
    private Producto producto;
    
    @NotNull
    @Column(name="valorizacion")
    private String valorizacion;
    
    
}
