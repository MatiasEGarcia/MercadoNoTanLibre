package MercadoNoTanLibre.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="producto_categorias")
public class ProductoCategoria implements Serializable{

    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_producto_categoria")
    private Long idProductoCategoria; /*en la bdd es int, a ver si fuciona*/
    
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="id_producto")  //referencia a id_producto
    private Producto producto;
    
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;
    
    
}