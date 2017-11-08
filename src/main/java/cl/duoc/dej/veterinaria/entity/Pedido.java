package cl.duoc.dej.veterinaria.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Pedido implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;    
    @ElementCollection
    private List<LineaPedido> lineasPedido;
    @ManyToOne
    private Cliente cliente;    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fecha;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public List<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public void setLineasPedido(List<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }
    
    
    
}
