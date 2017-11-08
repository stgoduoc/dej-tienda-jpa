package cl.duoc.dej.veterinaria.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class LineaPedido implements Serializable {
    
    @ManyToOne
    Producto producto;
    @Column(nullable = false)
    int cantidad;
    @Column(nullable = false)
    Long precio;

    public LineaPedido() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
    
    
    
}
