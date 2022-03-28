
package MercadoNoTanLibre.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="personas")
public class Persona implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_codigo")
    private Long idCodigo;/*en la bdd es int, a ver si fuciona*/
    
    @NotNull
    @OneToOne(cascade=CascadeType.ALL)
    private Usuario usuario;
    
    @NotNull
    @Column(name="nombres")
    private String nombres;
    
    @NotNull
    @Column(name="apellidos")
    private String apellidos;
    
    @NotNull
    @Column(name="activo")
    private boolean activo;
    
    @Column(name="telefono")
    private String telefono;
  
    @Column(name="email")
    private String email;
    
    @Column(name="direccion")
    private String direccion; 
            

    
    
}
