package com.revisao.ecommerce.dto;

import com.revisao.ecommerce.entities.ItemDoPedido;

public record ItemPedidoDTO(Integer quantidade, Double preco) {

    public ItemPedidoDTO(ItemDoPedido itemDoPedido) {
        this(itemDoPedido.getQuantidade(), itemDoPedido.getPreco());
    }
}
