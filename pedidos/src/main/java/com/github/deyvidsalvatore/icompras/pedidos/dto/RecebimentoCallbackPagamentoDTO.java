package com.github.deyvidsalvatore.icompras.pedidos.dto;

public record RecebimentoCallbackPagamentoDTO(
        Long codigo,
        String chavePagamento,
        boolean status,
        String observacoes
) {
}
