
package MercadoNoTanLibre.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int idUsuario;
    
    @NotNull
    @Column(name="nombre_usuario")
    private String username;
    
    @NotNull
    @Column(name="clave")
    private String password;
    
    //@Lob
    @Column(name="imagen")
    private String imagen;
    
    @NotNull
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="rol")
    private List<Rol> roles;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "usuario")
    private Persona persona;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario")
    private List<Pedido> pedidos;
    
    @OneToMany(fetch= FetchType.LAZY,mappedBy = "usuario")
    private List<Comentario> comentarios;
    
    
}
