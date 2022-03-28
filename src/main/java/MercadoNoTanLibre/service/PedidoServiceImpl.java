/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MercadoNoTanLibre.service;

import MercadoNoTanLibre.dao.PedidoDao;
import MercadoNoTanLibre.domain.Pedido;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoDao pedidoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Pedido> listarPedido() {
        return (List<Pedido>)pedidoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Pedido pedido) {
        pedidoDao.save(pedido);
    }

    @Override
    @Transactional
    public void eliminar(Pedido pedido) {
        pedidoDao.delete(pedido);
    }

    @Override
    @Transactional(readOnly=true)
    public Pedido encontrarPedido(Pedido pedido) {
        return pedidoDao.findById(pedido.getIdPedido()).orElse(null);
    }

}
