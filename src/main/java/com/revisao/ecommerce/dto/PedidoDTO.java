package com.revisao.ecommerce.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.revisao.ecommerce.entities.ItemDoPedido;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.StatusDoPedido;

public class PedidoDTO{
    private Long id;
    private Instant momento;
    private StatusDoPedido status;
    private Set<ItemPedidoDTO> itens = new HashSet<>();
    private Long clienteId;

    public PedidoDTO(Long id, Instant momento, StatusDoPedido status, Long clienteId, Set<ItemPedidoDTO> itens) {
        this.clienteId = clienteId;
        this.id = id;
        this.momento = momento;
        this.status = status;
        this.itens = itens;
    }

    public PedidoDTO(Pedido pedido){
        this.id = pedido.getId();
        this.momento = pedido.getMomento();
        this.status = pedido.getStatus();
        this.clienteId = pedido.getCliente().getId();
    }

    public PedidoDTO() {
    }

    public PedidoDTO(ItemDoPedido itemDoPedido) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public void setStatus(StatusDoPedido status) {
        this.status = status;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Set<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}

