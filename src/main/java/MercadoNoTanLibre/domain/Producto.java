package MercadoNoTanLibre.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "stock")
    private int stock;

    @NotNull
    @Column(name = "marca")
    private String marca;

    @NotNull
    @Column(name = "precio")
    private float precio;

    @Column(name = "imagen", columnDefinition = "BLOB")
    private String imagen;

    @Column(name = "precio_descuento")
    private float precioDescuento;

    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")//Cuando se elimine el producto deberia poder eliminar sus asociaciones con la categoria
    private List<ProductoCategoria> ProductoCategorias;

    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comentario")
    private List<Comentario> comentarios;

    public Producto() {

    }

    public Producto(long idProducto) {
        this.idProducto = idProducto;
    }

    //Para obtener las categorias asociadas a este producto a travez de la tabla ProductoCategorias
    public List<Categoria> getCategorias() {
        return getProductoCategorias().stream().map(ProductoCategoria -> ProductoCategoria.getCategoria()).collect(Collectors.toList());
    }

}
