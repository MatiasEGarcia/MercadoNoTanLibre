package MercadoNoTanLibre.service;

import java.util.List;

import MercadoNoTanLibre.domain.Pedido;

public interface PedidoService {
    
    public List<Pedido> listarPedido();
    
    public void guardar(Pedido pedido);
    
    public void eliminar(Pedido pedido);
    
    public Pedido encontrarPedido(Pedido pedido);
}
