package com.github.deyvidsalvatore.icompras.pedidos.dto;

import java.util.List;

public record NovoPedidoDTO(
        Long codigoCliente,
        DadosPagamentoDTO dadosPagamento,
        List<ItemPedidoDTO> itens) {
}