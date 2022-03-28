package MercadoNoTanLibre.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable{
        
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Long idPedido;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_usuario" , referencedColumnName = "id_usuario")
    private Usuario usuario;
    
    @NotNull
    @Column(name="direccion")
    private String direccion;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_producto") //referencia a id_producto
    private Producto producto;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_compra")
    private Date fechaCompra;


}
